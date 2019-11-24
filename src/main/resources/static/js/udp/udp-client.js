
function sendUdp() {
    var serverHost = $("#udp-server-host").val();
    var serverPort = $("#udp-server-port").val();
    var content = $("#udp-content").val();
    $.ajax({
        url: "/sendUdp",
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        async: true,
        data: JSON.stringify({
            "serverHost": serverHost,
            "serverPort": serverPort,
            "content": content
        }),
        success: function (res) {
            console.log('res data = ' + res);
            if (res === null || res === '') {

            } else {
                switchButton();
            }
        },
        error: function (e) {
            alert(JSON.stringify(e));
        }
    });
}

function switchButton() {
    var start = $("#udp-client-start");
    console.log(JSON.stringify(start));

    start.hide();
    var end = $("#udp-client-end");
    console.log(JSON.stringify(end));
    end.show();
}