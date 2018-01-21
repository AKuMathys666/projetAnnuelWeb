<?php
    include "header.php";
?>
    <section>
        
        <form action="login-result.php" method="POST">
            
            <div class="form-group login-css">
                <label for="email">Email address</label>
                <input name="email" type="email" class="form-control login-css" placeholder="Email">
            </div>
            
            <div class="form-group login-css">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control login-css" placeholder="Password">
            </div>
            
            <button type="submit" class="btn btn-default">Submit</button>
            
        </form>
        
    </section>

<?php
    include "footer.php";
?>