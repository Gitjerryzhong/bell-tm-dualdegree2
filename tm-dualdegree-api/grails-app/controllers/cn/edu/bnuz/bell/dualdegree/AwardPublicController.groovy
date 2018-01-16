package cn.edu.bnuz.bell.dualdegree

import cn.edu.bnuz.bell.security.SecurityService
import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize('hasRole("ROLE_DUALDEGREE_STUDENT")')
class AwardPublicController {
    AwardService awardService
    SecurityService securityService

    def index(String studentId) {
        renderJson(awardService.list(studentId))
    }

    def show(String studentId, Long id) {
        def form = awardService.getFormForShow(id)
        if (form.departmentId != securityService.departmentId) {
            renderBadRequest()
        } else {
           renderJson(form)
        }

    }
}
