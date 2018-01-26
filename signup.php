<?php
    include "header.php";
?>
    <section>
        
        <form class="reduce-css" action="signup-result.php" method="POST">
            
            <div class="form-group">
                <label for="email">Email address</label>
                <input name="email" type="email" class="form-control" placeholder="Email">
            </div>
            
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" placeholder="Password">
            </div>
            
            <div class="form-group">
                <label for="firstname">First Name</label>
                <input name="firstname" type="text" class="form-control" placeholder="firstname">
            </div>
            
            <div class="form-group">
                <label for="lastname">Last Name</label>
                <input name="lastname" type="text" class="form-control" placeholder="lastname">
            </div>
            
            <button type="submit" class="btn btn-default">Submit</button>
            
        </form>
        
    </section>

<?php
    include "footer.php";
?>