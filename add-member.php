<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>
        <div class="panel panel-default panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">Result</div>
<?php

    $data = array();                                                                    
    $data_string = json_encode($data);
        
    //print($_SESSION['userToken']);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
    

    //$result = addMember($header, $data_string, $_POST['user'], $_POST['team']);
            
    //$_SESSION["projectList"] = getListProjects($header, $data_string);
            
    $ch = curl_init('http://localhost:8080/teams/'.$_POST['team'].'/'.$_POST['user']);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
        'Content-Type: application/json',                                                                                
        'Content-Length: ' . strlen($data_string))  
    );
            
    $response = curl_exec($ch);
    //print_r($result);
    //echo "Created !!!";
            echo $response;

?>
        </div>
    </section>

<?php
    include "footer.php";
?>