var ajaxUrl = "ajax/meals/";
var ajaxUrlFilter = "ajax/meals/filter";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
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
                "desc"
            ]
        ]
    });
    makeEditable();
});

function filter() {
    var form = $("#filterForm");
    $.ajax({
        type: "GET",
        url: ajaxUrlFilter,
        data: form.serialize(),
        success: function () {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    });
}