<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>

<?php
        
    //print($_SESSION['userToken']);
    
    //echo '</br>';

    $data = array();                                                                    
    $data_string = json_encode($data);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);

    $result = getListTasks($header, $data_string);
    $_SESSION['taskList'] = getListTasks($header, $data_string);

    //print_r($result);
        //[project] => 5a242feb50f24621585a812f [owner] => 59fb196ccacbc51dc86cf4f1 [title] => task-4 [startDate] => 2017-01-04T00:00:00.000Z [__v] => 0 [endDate] => 2017-01-05T10:10:10.000Z
        echo "<table class=".'"table-css table-bordered table-responsive table-hover table-striped"'.">";
        echo "<tr>
        <th class='title-css'>Id</th>
        <th class='title-css'>Title</th>
        <th class='title-css'>Start date</th>
        <th class='title-css'>End date</th>
        <th class='title-css'>Owner</th>
        <th class='title-css'>Project</th>
        <th class='title-css'>__v</th>
        <th class='title-css'>Times</th>
        </tr>";
        
        displayTasks($_SESSION['taskList']);

?>
        
    </section>

<?php
    include "footer.php";
?>