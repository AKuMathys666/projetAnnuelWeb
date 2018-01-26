<?php
    include "header.php";
    require_once "functions.php";
?>
    <section>
        <div class="panel panel-primary panelList-css">
            <!-- Default panel contents -->
            <div class="panel-heading">User Lists</div>
            <div class="panel-body">
<?php
        
    //print($_SESSION['userToken']);
    
    //echo '</br>';

    $data = array();                                                                    
    $data_string = json_encode($data);
        
    $header = array('Content-Type: application/json', 'Content-Length: '.strlen($data_string), 'Authorization: '.$_SESSION['userToken']);
        
    $result = getListUsers($header, $data_string);
    $_SESSION['userList'] = getListUsers($header, $data_string);
            
    $team = getTeam($header, $data_string, $_GET['team']);
    
    //print_r($result);
    echo "<table class=".'"table-css table-bordered table-responsive table-hover table-striped"'.">";
    echo "<tr>
    <!--<th class='title-css'>Id</th>-->
    <th class='title-css'>Email</th>
    <th class='title-css'>Last name</th>
    <th class='title-css'>First name</th>
    <th class='title-css'>Action</th>
    </tr>";
        
    displayMembers($team['members'], $_SESSION['userList'], $team['_id']);
        

?>
            </div>
        </div>
            
        <div class="panel panel-info reduce-css">
            <h3>Add member</h3>
        <!-- Default panel contents -->
            <div class="panel-body">
                <form class="reduce-css" action="add-member.php" method="POST">

                <div class="form-group">
                    <label for="task">Users</label>
                    <select class="form-control" name="user">
                        <?php
                            for($i=0; $i<count($_SESSION['userList']); $i++){
                                echo "<option value=".$_SESSION['userList'][$i]['_id'].">";
                                echo $_SESSION['userList'][$i]['email'];
                                echo "</option>";
                            }
                        ?>
                    </select>
                    <input name="team" type="hidden" <?php echo "value='" . $team['_id'] . "'"; ?>>
                </div>

                <button type="submit" class="btn btn-default">Add</button>

                </form>
            </div>
        </div>
            
            
    </section>

<?php
    include "footer.php";
?>