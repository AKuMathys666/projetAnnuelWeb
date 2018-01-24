<?php
    include "header.php";
    require_once "functions.php";
?>
<head>
<script type="text/javascript">
    var c=0
    var t
    //window.onload=
    function timedCount()
    {
        document.getElementById('txt').value=c
        c=c+1
        t=setTimeout("timedCount()",1000)
    }
    window.onload = timedCount;
</script>
</head>
<body onload="timedCount()">
    <section>

<?php
    
    $currentTime = date(DATE_ISO8601, time());


    $data = array("title" => $_POST['title'], "startDate" => $currentTime, "times" => '0');                                                                    
    $data_string = json_encode($data);
        
    //print($_SESSION['userToken']);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
    
    //print(json_encode($header));
    //print($data_string);
    
    $url = 'http://localhost:8080/tasks/' . $_POST['project'];
        
    //print(findProjectByTitle($_SESSION['projectList'], $_POST['title']));
        
    //print($url);

    $ch = curl_init($url);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                                                                                                                   
    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
        
    //print_r($result);
    //print($result["tasks"][count($result["tasks"])-1]);
        
    //$_SESSION['userToken'] = $result;

    $_SESSION['taskList'] = getListTasks($header, $data_string);

    //echo "Task created !";
    
    //echo "</br>";
        
    //echo "Counting...";
?>
        <!--
<form>
    <input type="text" id="txt">
</form>-->

<!--<form>
<input type="button" value="begin！" onClick="timedCount()">
<input type="text" id="txt">
</form>-->
    <div class="input-group reduce-css">
        <span class="input-group-addon" id="basic-addon1">Task created ! Counting(Seconds)...</span>
        <input type="text" id="txt" class="form-control" placeholder="count time" aria-describedby="basic-addon1">
    </div>

<?php
        
    echo "</br>";
    
    //print($_SESSION['userToken']);


        
        echo '<form class="reduce-css" action="tasks-end.php" method="POST">' ;
        echo '<input name="task" type="hidden" value ="'.$result["tasks"][count($result["tasks"])-1].'">';
        echo '<button type="submit" class="btn btn-default">Stop</button>';
        echo '</form>';
?> 
    </section>
</body>
<?php
    include "footer.php";
?>