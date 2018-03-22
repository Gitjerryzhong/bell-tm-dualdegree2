package cn.edu.bnuz.bell.dualdegree

import cn.edu.bnuz.bell.http.BadRequestException
import cn.edu.bnuz.bell.workflow.Event
import cn.edu.bnuz.bell.workflow.ListCommand
import cn.edu.bnuz.bell.workflow.ListType
import cn.edu.bnuz.bell.workflow.commands.AcceptCommand
import cn.edu.bnuz.bell.workflow.commands.RejectCommand
import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize('hasAuthority("PERM_DUALDEGREE_PAPER_APPROVE")')
class PaperApprovalController {
    PaperApprovalService paperApprovalService

    def index(String approverId, ListCommand cmd) {
        renderJson(paperApprovalService.list(approverId, cmd))
    }

    def show(String approverId, Long paperApprovalId, String id, String type) {
        ListType listType = ListType.valueOf(type)
        if (id == 'undefined') {
            renderJson paperApprovalService.getFormForReview(approverId, paperApprovalId, listType)
        } else {
            renderJson paperApprovalService.getFormForReview(approverId, paperApprovalId, listType, UUID.fromString(id))
        }
    }

    def patch(String approverId, Long id, String op) {
        Long formId = id
        if (!formId) {
            formId = params.getLong("paperApprovalId")
        }
        def operation = Event.valueOf(op)
        switch (operation) {
            case Event.FINISH:
                paperApprovalService.finish(approverId, id)
                break
            case Event.REJECT:
                def cmd = new RejectCommand()
                bindData(cmd, request.JSON)
                cmd.id = formId
                paperApprovalService.reject(approverId, cmd)
                break
            default:
                throw new BadRequestException()
        }

        renderJson paperApprovalService.getFormForReview(approverId, formId, ListType.TODO)
    }
}
