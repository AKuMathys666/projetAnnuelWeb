<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>
        <div class="panel panel-default panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">User Lists</div>
<?php
                

    $data = array();                                                                    
    $data_string = json_encode($data);
        
    //print($_SESSION['userToken']);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
    
    //print(json_encode($header));
    //print($data_string);
    
    $url = 'localhost:8080/teams/' . $_GET['id'] . '/' . $_GET['idUser'];
        
    //print(findProjectByTitle($_SESSION['projectList'], $_POST['title']));
        
    //print($url);

    $ch = curl_init($url);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                                                                                                                   
    $result = curl_exec($ch);
        
    $_SESSION['projectList'] = getListProjects($header, $data_string);
        
    //print($result);
        
    //$_SESSION['userToken'] = $result;
        
    echo "<h1 style='text-align:center'>Member deleted!</br></h1>";
    
    
    //print($_SESSION['userToken']);


?>
        </div>
    </section>

<?php
    include "footer.php";
?>