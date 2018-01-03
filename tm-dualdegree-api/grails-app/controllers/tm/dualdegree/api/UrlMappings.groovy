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

        "/students"(resource: 'student', includes: []) {
            "/degree-applications"(resource: 'applicationForm')
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
