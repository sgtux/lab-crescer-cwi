const http = require('http')

let logs = []
let id = 1

function formatDate(date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();

    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
}

const app = http.createServer((req, res) => {

    if (req.url === '/view-logs') {
        console.log(logs)
        let body = '<html><style>table, th, td {border: 1px solid #ccc;border-collapse: collapse;padding:10px }</style>'
        body += '<body style="background-color: #222;color: #ddd;font-family: Roboto, Arial, Helvetica;"><table>'
        body += '<thead><tr><th>Id</th><th style="width:150px">Date</th><th style="width:400px">Url</th><th>Headers</th></tr></thead><tbody>'

        for (const log of logs) {
            body += '<tr>'
            body += `<td>${log.id}</td>`
            body += `<td>${formatDate(log.date)}</td>`
            body += `<td>${log.url}</td>`
            body += '<td>'
            for (const head in log.headers) {
                body += `<b>${head}</b>: ${log.headers[head]}<br />`
            }
            body += '</td>'
            body += '</tr>'
        }

        body += '</tbody></table><br/><br/>'
        body += '<button style="font-weight: bold;padding: 10px;" onclick="window.location=\'/reset\'">CLEAR LOGS</button>'
        body += '</body></html>'
        return res.end(body)
    }

    if (req.url === '/reset') {
        logs = []
        return res.writeHead(301, { "Location": "/view-logs" }).end()
    }

    logs.unshift({
        id: id++,
        url: req.url,
        headers: req.headers,
        date: new Date()
    })

    res.end('<html><body><h2>Hello World!</h2><br /><br /><button style="font-weight: bold;padding: 10px;" onclick="window.location=\'/view-logs\'">LOGS</button></body></html>')
})

const PORT = process.env.NODE_PORT || 8088

app.listen(PORT, () => console.log(`Listening on ${PORT} port.`))