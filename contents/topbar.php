<div class="navbar-inner">
                        <div class="container-fluid">
                            <a class="brand" href="home.php"><i class="icon-home icon-white"></i> SMS ME Back</a>
                            <ul class="nav user_menu pull-right">
                                <li class="hidden-phone hidden-tablet">
                                    <div class="nb_boxes clearfix">
                                       <!--Notification Menu and Icon goes here-->
                                    </div>
                                </li>
								<li class="divider-vertical hidden-phone hidden-tablet"></li>
                                <li class="dropdown">
                                   <!--Drop down list goes here-->
                                </li>
                                <li class="divider-vertical hidden-phone hidden-tablet"></li>
                                <li class="dropdown">
                               <?php 
							   if($uid=='')
							   {
								   echo '';
							   }
							   else
							   {
								   ?>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="img/user_avatar.png" alt="" class="user_avatar" /> <?php echo $name; ?> <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
										<li><a href="profile.php">My Profile</a></li>
										<li><a href="javascrip:void(0)">Another action</a></li>
										<li class="divider"></li>
										<li><a href="logout.php">Log Out</a></li>
                                    </ul>
                                    
                                    <?php
							   }
							   
							   ?>
                                </li>
                            </ul>
							<a data-target=".nav-collapse" data-toggle="collapse" class="btn_menu">
								<span class="icon-align-justify icon-white"></span>
							</a>
                            <!--Top menu goes here-->
                        </div>
                    </div>