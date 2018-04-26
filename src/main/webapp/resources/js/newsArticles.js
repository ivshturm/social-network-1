var ajaxUrl = "ajax/profile/home/news/";
var dataTableApi;

// http://api.jquery.com/jQuery.ajax/#using-converters
$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            var json = JSON.parse(stringData);
            $(json).each(function () {
                this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});

$(function () {
    dataTableApi = $('#dataTable').DataTable(extendsOpts({
        "columns": [
            {
                "render": selectArticle,
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "userFullName"
            },
            {
                "data": "name"
            },
            {
                "data": "dateTime"
            }
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass('item');
        }
    }));
});

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "language": {
                "search": "Искать по статьям"
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}

function selectRow(id) {
    document.location.href = "article/"+id;
}

function selectArticle(data, type, row) {
    if (type === "display") {
        return "<a onclick='selectRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span></a>";
    }
}