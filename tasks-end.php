<?php
    include "header.php";
    require_once "functions.php";
?>
<section>
    <div class="panel panel-default panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">Task Lists</div>
<?php

    $currentTime = date(DATE_ISO8601, time());

/*********************/    
  
    echo "startDate: ";
    echo strtotime(findTaskById($_SESSION['taskList'], $_POST['task'])['startDate']);
    
    echo "</br>";
    echo "endDate: ";
    echo strtotime($currentTime);
    
    echo "</br>";
    echo "diff: ";
    echo $diff = abs(strtotime(findTaskById($_SESSION['taskList'], $_POST['task'])['startDate'])-strtotime($currentTime));
    
    echo "</br>";
    echo "oldTimes: ";
    echo $oldTimes = findTaskById($_SESSION['taskList'], $_POST['task'])['times'];
    
    echo "</br>";
    
    
/***********************/    
    //$times = abs(strtotime(findTaskById($_SESSION['taskList'], $_POST['task'])['startDate'])-strtotime($currentTime) + findTaskById($_SESSION['taskList'], $_POST['task'])['times']);
    echo "newTimes: ";
    echo $times = $diff + $oldTimes;
    
    echo "</br>date format: ";
    echo displayTime($times);

    $data = array("endDate" => $currentTime, "times" => $times);                                                                    
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
    $result = curl_exec($ch);

    echo "</br>Stopped!";
    
    $_SESSION['taskList'] = getListTasks($header, $data_string);

?>
    </div>
</section>

<?php
    include "footer.php";
?>