package com.my.project.utils;

/**
 * @Title WechatUtils.java
 * @Description 企业微信机器人工具类
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * WechatUtils: 企业微信机器人工具类.
 * https://work.weixin.qq.com/api/
 */
@Component
@Slf4j
@Data
public class WechatUtils {

    /**
     * 企业微信机器人webhook
     */
    private static String WEB_HOOK;

    /**
     * Http客户端
     */
    private static HttpClient httpclient = HttpClients.createDefault();

    /**
     * 线程执行器
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(
            15,
            45,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1024),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Value("${project.wechat.webhook}")
    private String webHook;

    @PostConstruct
    public void setWebhook() {
        WechatUtils.WEB_HOOK = getWebHook();
    }

    private static final BlockingQueue<Pair<String, Message>> blockingQueue = new ArrayBlockingQueue<>(1000);

    static {
        //企业微信发送失败，补发机制 5分钟调度一次，直至发送成功
        executorService.execute(() -> {
            //noinspection InfiniteLoopStatement
            for (; ; ) {
                try {
                    //休眠5分钟
                    Thread.sleep(5 * 60 * 1000);
                    for (; ; ) {
                        Pair<String, Message> poll = blockingQueue.poll();
                        if (poll == null) {
                            break;
                        }
                        Message message = poll.getValue();
                        log.info("企业微信补发推送通知：{}", message.toString());
                        postWebHook(poll.getKey(), message);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });
    }

    /**
     * 同步发送消息
     *
     * @param message 消息体
     */
    public static void sendMessage(Message message) {
        log.info("企业微信提醒返回结果：{}", postWebHook(WEB_HOOK, message));
    }

    /**
     * 异步发送消息
     *
     * @param message 消息体
     */
    public static void sendMessageAsync(Message message) {
        executorService.submit(() -> log.info("企业微信提醒返回结果：{}", postWebHook(WEB_HOOK, message)));
    }

    /**
     * 同步发送消息
     *
     * @param webHook 企业微信webHook
     * @param message 消息体
     */
    public static void sendMessage(String webHook, Message message) {
        log.info("企业微信提醒返回结果：{}", postWebHook(webHook, message));
    }

    /**
     * 异步发送消息
     *
     * @param webHook 企业微信webHook
     * @param message 消息体
     */
    public static void sendMessageAsync(String webHook, Message message) {
        executorService.submit(() -> log.info("企业微信提醒返回结果：{}", postWebHook(webHook, message)));
    }

    /**
     * Post消息
     *
     * @param wechatHook 企业微信wechatHook
     * @param message    消息体
     * @return 发送结果
     */
    @SuppressWarnings("unchecked")
    private static SendResult postWebHook(String wechatHook, Message message) {
        SendResult sendResult = new SendResult();
        try {
            HttpPost httppost = new HttpPost(wechatHook);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
            httppost.setEntity(se);

            HttpResponse response;
            response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = JSONObject.parseObject(result);
                Integer errcode = obj.getInteger("errcode");
                sendResult.setErrorCode(errcode);
                sendResult.setErrorMsg(obj.getString("errmsg"));
                sendResult.setSuccess(errcode.equals(0));
            } else {
                blockingQueue.offer(new Pair(wechatHook, message));
            }
        } catch (Exception e) {
            log.error("发送企业微信提醒失败：", e);
            blockingQueue.offer(new Pair(wechatHook, message));
        }
        return sendResult;
    }


    /**
     * SendResult: 发送结果
     *
     * @author shenzhibing
     * @since 2018年4月28日
     */
    @Data
    public static class SendResult {
        /**
         * 是否成功
         */
        private boolean isSuccess = true;

        /**
         * 错误码
         */
        private Integer errorCode;

        /**
         * 错误消息
         */
        private String errorMsg;

        @Override
        public String toString() {
            Map<String, Object> items = new HashMap<String, Object>();
            items.put("errorCode", errorCode);
            items.put("errorMsg", errorMsg);
            items.put("isSuccess", isSuccess);
            return JSON.toJSONString(items);
        }
    }

    /**
     * 消息接口
     */
    interface Message {
        /**
         * 返回消息的Json格式字符串
         *
         * @return 消息的Json格式字符串
         */
        String toJsonString();
    }

    @Data
    public static class TextMessage implements Message {

        /**
         * 消息内容
         */
        private String text;
        /**
         * 群id
         */
        private String chatid;

        public TextMessage(String text, String chatid) {
            this.text = text;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "text");
            Map<String, String> textContent = new HashMap<String, String>(1);
            if (StringUtils.isBlank(text)) {
                throw new IllegalArgumentException("text should not be blank");
            }
            textContent.put("content", text);
            items.put("text", textContent);
            items.put("safe", 0);

            return JSON.toJSONString(items);
        }

    }

    @Data
    public static class ImgMessage implements Message {

        /**
         * 标题
         */
        private String tittle;

        /**
         * 描述
         */
        private String description;

        /**
         * 链接
         */
        private String url;

        /**
         * 图片地址
         */
        private String picurl;

        /**
         * 群id
         */
        private String chatid;

        public ImgMessage(String tittle, String description, String url, String picurl, String chatid) {
            this.tittle = tittle;
            this.description = description;
            this.url = url;
            this.picurl = picurl;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "news");
            Map<String, List> textContent = new HashMap<>();
            List jsonArray = new ArrayList();
            Map<String, String> pic = new HashMap<>(8);
            pic.put("title", tittle);
            pic.put("description", description);
            pic.put("url", url);
            pic.put("picurl", picurl);
            jsonArray.add(pic);
            textContent.put("articles", jsonArray);
            items.put("news", textContent);
            items.put("safe", 0);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }

    @Data
    public static class MarkDownMessage implements Message {

        /**
         * md格式消息
         */
        private String mdMsg;

        /**
         * 群id
         */
        private String chatid;

        public MarkDownMessage(String mdMsg, String chatid) {
            this.mdMsg = mdMsg;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(3);
            items.put("chatid", chatid);
            items.put("msgtype", "markdown");
            Map<String, String> textContent = new HashMap<>(1);
            textContent.put("content", mdMsg);
            items.put("markdown", textContent);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }

    @Data
    public static class VoiceMessage implements Message {

        /**
         * 语音id
         */
        private String voiceId;

        /**
         * 群id
         */
        private String chatid;

        public VoiceMessage(String voiceId, String chatid) {
            this.voiceId = voiceId;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "voice");
            Map<String, String> textContent = new HashMap<>(1);
            textContent.put("media_id", voiceId);
            items.put("voice", textContent);
            items.put("safe", 0);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }

    @Data
    public static class VideoMessage implements Message {

        /**
         * 视频id
         */
        private String videoId;

        /**
         * 描述
         */
        private String description;

        /**
         * 群id
         */
        private String chatid;

        public VideoMessage(String videoId, String description, String chatid) {
            this.videoId = videoId;
            this.description = description;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "video");
            Map<String, String> textContent = new HashMap<>(2);
            textContent.put("media_id", videoId);
            textContent.put("description", description);
            items.put("video", textContent);
            items.put("safe", 0);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }

    @Data
    public static class FileMessage implements Message {

        /**
         * 文件id
         */
        private String fileId;

        /**
         * 群id
         */
        private String chatid;

        public FileMessage(String fileId, String chatid) {
            this.fileId = fileId;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "file");
            Map<String, String> textContent = new HashMap<>(1);
            textContent.put("media_id", fileId);
            items.put("file", textContent);
            items.put("safe", 0);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }

    @Data
    public static class TextCardMessage implements Message {

        /**
         * 主题
         */
        private String title;

        /**
         * 描述
         */
        private String description;

        /**
         * url
         */
        private String url;

        /**
         * 更多
         */
        private String btntxt;

        /**
         * 群id
         */
        private String chatid;

        public TextCardMessage(String title, String description, String url, String btntxt, String chatid) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.btntxt = btntxt;
            this.chatid = chatid;
        }

        @Override
        public String toJsonString() {
            Map<String, Object> items = new HashMap<String, Object>(4);
            items.put("chatid", chatid);
            items.put("msgtype", "textcard");
            Map<String, String> textContent = new HashMap<>(4);
            textContent.put("title", title);
            textContent.put("description", description);
            textContent.put("url", url);
            textContent.put("btntxt", btntxt);
            items.put("textcard", textContent);
            items.put("safe", 0);
            return StringEscapeUtils.unescapeJavaScript(JSONObject.toJSONString(items));
        }
    }


}


