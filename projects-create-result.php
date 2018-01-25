<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>
        <div class="panel panel-default panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">Result</div>
<?php

    $data = array("title" => $_POST['title']);                                                                    
    $data_string = json_encode($data);
        
    //print($_SESSION['userToken']);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
    

    $result = createProject($header, $data_string);
            
    $_SESSION["projectList"] = getListProjects($header, $data_string);
    //print_r($result);
    echo "Created !!!"

?>
        </div>
    </section>

<?php
    include "footer.php";
?>