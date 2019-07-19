package com.finance.core.act.controller;
import com.finance.common.annotation.Permission;
import com.finance.common.annotation.PermissionType;
import com.finance.core.act.entity.Acticle;
import com.finance.core.base.BaseController;
import com.finance.core.act.service.IActicleService;
import com.finance.common.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author rstyro
 * @since 2019-3-26
 */
@Controller
@RequestMapping("/act/acticle")
public class ActicleController extends BaseController {

    private final static String qxurl = "act/acticle/list";

    @Autowired
    private IActicleService acticleService;

    @GetMapping("/list")
    public String list(Model model, PageDto dto) throws Exception {
        System.out.println(dto);
        model.addAttribute("list",acticleService.getList(dto));
        return "page/act/acticle_list";
    }

    @PostMapping(value="/add")
    @ResponseBody
    @Permission(url = qxurl,type = PermissionType.ADD)
    public Object add(Acticle item) throws Exception {
        item.setId(null);
        return acticleService.add(item,this.getSession());
    }

    @PostMapping(value="/edit")
    @ResponseBody
    @Permission(url = qxurl,type = PermissionType.EDIT)
    public Object edit(Acticle item) throws Exception {
        return acticleService.edit(item,this.getSession());
    }

    @PostMapping(value="/del")
    @ResponseBody
    @Permission(url = qxurl,type = PermissionType.DEL)
    public Object del(Long id) throws Exception {
        return acticleService.del(id,this.getSession());
    }

    @GetMapping(value="/query")
    @ResponseBody
    @Permission(url = qxurl,type = PermissionType.QUERY)
    public Object query(Long id) throws Exception {
        return acticleService.getDetail(id);
    }

}
