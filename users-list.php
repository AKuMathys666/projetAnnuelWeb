<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>
        <div class="panel panel-default panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">User Lists</div>
<?php
        
    //print($_SESSION['userToken']);
    
    //echo '</br>';

    $data = array();                                                                    
    $data_string = json_encode($data);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
        
    $result = getListUsers($header, $data_string);
    $_SESSION['userList'] = getListUsers($header, $data_string);
    
    //print_r($result);
    echo "<table class=".'"table-css table-bordered table-responsive table-hover table-striped"'.">";
    echo "<tr>
    <th class='title-css'>Id</th>
    <th class='title-css'>Email</th>
    <th class='title-css'>Role</th>
    <th class='title-css'>Tasks</th>
    <th class='title-css'>Last name</th>
    <th class='title-css'>First name</th>
    <th class='title-css'>__v</th>
    </tr>";
        
    displayUsers($_SESSION['userList'], $_SESSION['taskList']);
        

?>
        </div>
    </section>

<?php
    include "footer.php";
?>