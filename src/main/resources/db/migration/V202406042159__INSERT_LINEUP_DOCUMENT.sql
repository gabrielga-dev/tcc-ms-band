INSERT INTO document_template(id, description, content)
VALUES (2, 'Template to list the lineup of a quote.',
        '<!DOCTYPE html>
<html>
<head>
    <title>Listagem de músicos</title>
    <style>
        body{
            font-size: 12pt;
            font-family: Calibri;
            text-align: justify;
            width: 210mm;
            height: 297mm;
            line-height: 1;
        }
        .paginacao{
            padding-top: 2cm;
            padding-bottom: 2cm;
            padding-left: 3cm;
            padding-right: 1.5cm;
            width: 100%;
            height: 100%;
        }
        .text-center{
            text-align: center;
        }

        table{
            width: 100%;
            border-collapse: collapse;
        }
        .titulo{
            font-weight: bold;
            font-size: 20px;
        }
        .padding{
            padding-top: 5px;
            padding-bottom: 5px;
            padding-right: 5px;
            padding-left: 5px;
        }
        .col-10{
            width: 100%;
        }
        .avatar{
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <h3 class="text-center">Lineup para o evento <span th:text="${eventName}"></span></h3>
    <h4 class="text-center">Banda <span th:text="${bandName}"></span></h4>

    <table border="1" class="text-center">
        <thead>
            <tr>
                <td class="padding titulo">Nome</td>
                <td class="padding titulo">Sobrenome</td>
                <td class="padding titulo">Email</td>
            </tr>
        </thead>
        <tbody th:each="musician: ${musicians}">
            <tr>
                <td class="padding">
                    <span th:text="${musician.firstName}"></span>
                </td>
                <td class="padding">
                    <span th:text="${musician.lastName}"></span>
                </td>
                <td class="padding">
                    <span th:text="${musician.email}"></span>
                </td>
            </tr>
        </tbody>
    </table>
    <h5 class="text-center">Documento expedido em: <span th:text="${generatedAt}"></span></h5>
</body>
</html>
        ');
