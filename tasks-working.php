<?php
    include "header.php";
    require_once "functions.php";
?>
<<<<<<< HEAD

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
</script>
</head>
<body>
    <?php

        $currentTime = date(DATE_ISO8601, time());

        $data = array("startDate" => $currentTime);                                                                    
        $data_string = json_encode($data);

        //print($_SESSION['userToken']);

        $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);

        //print(json_encode($header));
        //print($data_string);

        $url = 'http://localhost:8080/tasks/' . $_POST['task'];

        //print(findProjectByTitle($_SESSION['projectList'], $_POST['title']));

        //print($url);

        $ch = curl_init($url);                                                                      
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");                                                                     
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                                                                                                                   
        $response = curl_exec($ch);

        $result = json_decode($response, true);

        //print_r($result);
        //print($result["tasks"][count($result["tasks"])-1]);

        //$_SESSION['userToken'] = $result;
        echo "Working !";

        echo "</br>";

        echo "Counting...";
    ?>

            <!--
    <form>
        <input type="text" id="txt">
    </form>-->

    <form>
    <input type="button" value="begin！" onClick="timedCount()">
    <input type="text" id="txt">
    </form>


    <?php

        echo "</br>";

        //print($_SESSION['userToken']);

        echo '<form action="tasks-end.php" method="POST">' ;
        echo '<input name="task" type="hidden" value ="'.$_POST['task'].'">';
        echo '<button type="submit" class="btn btn-default">Stop</button>';
        echo '</form>';
    ?> 
</body>
=======
<?php
    
    $currentTime = date(DATE_ISO8601, time());

    $data = array("startDate" => $currentTime);                                                                    
    $data_string = json_encode($data);
        
    //print($_SESSION['userToken']);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
    
    //print(json_encode($header));
    //print($data_string);
    
    $url = 'http://localhost:8080/tasks/' . $_POST['task'];
        
    //print(findProjectByTitle($_SESSION['projectList'], $_POST['title']));
        
    //print($url);

    $ch = curl_init($url);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                                                                                                                   
    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
        
    //print_r($result);
    //print($result["tasks"][count($result["tasks"])-1]);
        
    //$_SESSION['userToken'] = $result;
    echo "Working !";
    
    echo "</br>";
        
    echo "Counting...";
?>

<?php
        
    echo "</br>";
    
    //print($_SESSION['userToken']);
      
    echo '<form action="tasks-end.php" method="POST">' ;
    echo '<input name="task" type="hidden" value ="'.$_POST['task'].'">';
    echo '<button type="submit" class="btn btn-default">Stop</button>';
    echo '</form>';
?> 

>>>>>>> 61b689290dffe059394936b2b3c9f4a4d2b8cbd4
<?php
    include "footer.php";
?>