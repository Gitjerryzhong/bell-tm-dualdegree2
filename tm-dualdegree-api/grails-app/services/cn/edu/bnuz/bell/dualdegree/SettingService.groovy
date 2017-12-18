package cn.edu.bnuz.bell.dualdegree

import cn.edu.bnuz.bell.organization.Department
import cn.edu.bnuz.bell.organization.Teacher
import grails.gorm.transactions.Transactional

@Transactional
class SettingService {

    /**
     * 管理员列表
     */
    def list() {
        DeptAdmin.executeQuery'''
select new map(
da.id as id,
d.id as departmentId,
d.name as departmentName,
t.id as teacherId,
t.name as teacherName
)
from DeptAdmin da join da.department d join da.teacher t
order by d.name
'''
    }

    /**
     * 保存管理员设置
     */
    def create(DeptAdminCommand cmd) {
        DeptAdmin form = new DeptAdmin(
                department:         Department.load(cmd.departmentId),
                teacher:            Teacher.load(cmd.teacherId)
        )

        form.save()
        return form
    }

    /**
     * 删除
     * @param id
     * @return
     */
    def delete(Long id) {
        def form = DeptAdmin.get(id)
        if (form) {
            form.delete()
        }
    }
}