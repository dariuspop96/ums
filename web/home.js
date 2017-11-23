
function studentsScript(){
    $('.table').remove();
    App.utils.createGrid({
        dataUrl: "http://localhost:8080/user/student/list",
        columns: ['last_name','first_name', 'email']
    })
}
function teachersScript(){
    $('.table').remove();
    App.utils.createGrid({
        dataUrl: "http://localhost:8080/user/teacher/list",
        columns: ['last_name','first_name', 'email']
    })
}
function groupsScript() {
    $('.table').remove();
    App.utils.createGrid({
        dataUrl: "http://localhost:8080/group/list",
        columns: ['name']
    })
}
function semestersScript() {
    $('.table').remove();
    App.utils.createGrid({
        dataUrl: "http://localhost:8080/semester/list",
        columns: ['year', 'index']
    })
}
function coursesScript() {
    $('.table').remove();
    App.utils.createGrid({
        dataUrl: "http://localhost:8080/course/list",
        columns: ['name']
    })
}




