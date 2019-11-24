
function sendUdpStart() {
    var serverHost = $("#udp-server-host").val();
    var serverPort = $("#udp-server-port").val();
    var clientPort = $("#udp-client-port").val();
    var content = $("#udp-content").val();
    $.ajax({
        url: "/sendUdp",
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        async: true,
        data: JSON.stringify({
            "serverHost": serverHost,
            "serverPort": serverPort,
            "clientPort": clientPort,
            "content": content,
            "method": "start"
        }),
        success: function (res) {
            console.log('res data = ' + res);
            if (res === null || res === '') {

            } else {
                showProcessBar()
                switchButtonToEnd();
            }
        },
        error: function (e) {
            alert(JSON.stringify(e));
        }
    });
}

function sendUdpEnd() {
    var serverHost = $("#udp-server-host").val();
    var serverPort = $("#udp-server-port").val();
    var clientPort = $("#udp-client-port").val();
    var content = $("#udp-content").val();
    $.ajax({
        url: "/sendUdp",
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        async: true,
        data: JSON.stringify({
            "serverHost": serverHost,
            "serverPort": serverPort,
            "clientPort": clientPort,
            "content": content,
            "method": "end"
        }),
        success: function (res) {
            console.log('res data = ' + res);
            if (res === null || res === '') {

            } else {
                hideProcessBar();
                switchButtonToStart();
            }
        },
        error: function (e) {
            alert(JSON.stringify(e));
        }
    });
}


function switchButtonToEnd() {
    var start = $("#udp-client-start");
    start.hide();
    var end = $("#udp-client-end");
    end.show();
}

function switchButtonToStart() {
    var end = $("#udp-client-end");
    end.hide();
    var start = $("#udp-client-start");
    start.show();
}

function showProcessBar() {
    $("#process-bar").show();
}

function hideProcessBar() {
    $("#process-bar").hide();
}