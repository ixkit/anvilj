<#assign base=springMacroRequestContext.getContextUrl("")>

<!DOCTYPE html>
<html>
<head lang="en">
<title>Spring Boot FreeMarker</title>
</head>
<body>
	AnvilxStudio
   <script type="text/javascript">
        const queryString = window.location.search;
        console.log(queryString);
       // alert(queryString);
        function go(){
            const targetUrl ="${base}/studio/index.html?" + queryString;

            window.location.href = targetUrl;
        }
        document.addEventListener("DOMContentLoaded", (event) => {
           console.log("DOM 完全加载和解析");
           setTimeout('go()',1000*0);
        });
   </script>
</body>
</html>