package cn.edu.bnuz.bell.dualdegree

import cn.edu.bnuz.bell.organization.Department
import cn.edu.bnuz.bell.organization.Teacher

/**
 * 论文指导老师
 */
class Mentor {

    Teacher teacher

    /**
     * 聘用部门
     */
    Department department

    static mapping = {
        comment '论文指导老师'
        table       schema: 'tm_dual'
        id generator: 'identity', comment: '无意义ID'
        department  comment: '聘用部门'
        teacher     comment: '教师'
    }

    static constraints = {
        teacher unique: 'department'
    }
}
