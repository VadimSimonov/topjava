var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(document).ready(function() {
    datatableApi = $("#datatable").DataTable({
        $(".btn-slide").click(function () {
        $("#panel").slideToggle("slow");
        $(this).toggleClass("active");
    });
})
    ;
}
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dataTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});