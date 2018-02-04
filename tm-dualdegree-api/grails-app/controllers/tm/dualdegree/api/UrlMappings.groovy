package tm.dualdegree.api

class UrlMappings {

    static mappings = {

        "/agreements"(resources: 'agreement')
        "/agreement-publics"(resources: 'agreementPublic', includes: ['index', 'show'])
        "/settings"(resources: 'setting')

        "/departments"(resources: 'department', includes: []) {
            "/students"(resources: 'studentAbroad')
            "/awards"(resources: 'award')
            "/agreements"(controller: 'agreementPublic', action: 'agreementsOfDept', method: 'GET')
        }

        "/students"(resources: 'student', includes: []) {
            "/awards"(resources: 'awardPublic', ['show'])
            "/applications"(resources: 'applicationForm') {
                "/approvers"(controller: 'applicationForm', action: 'approvers', method: 'GET')
            }
        }

        "/approvers"(resources: 'approver', includes: []) {
            "/applications"(resources: 'applicationApproval', includes: ['index', 'show']) {
                "/workitems"(resources: 'applicationApproval', includes: ['show', 'patch'])
            }
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
