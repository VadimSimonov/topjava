var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
                "ajax": {
                    "url": ajaxUrl,
                        "dataSrc": ""
                },
        "columns": [
            {
                "data": "dateTime"
                                , "render": function (data, type, row) {
                                // debugger;
                                    if (type === "display") {
                                        return dataFormat(data);
                                    }
                                return data;
                            }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
        , "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? "exceeded" : "normal")
        }
        , "initComplete": makeEditable
    });

    });
