<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Newline Template</title>
<!--
Newline Template
https://templatemo.com/tm-503-newline
-->
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="/css/fontAwesome.css">
        <link rel="stylesheet" href="/css/templatemo-style.css">
        <link rel="stylesheet" href="/style.css">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <script  src="/script.js"></script>
  		<script lang="javascript" src="xlsx.full.min.js"></script>
    </head>
    <body>

        <div class="overlay"></div>
        <section class="top-part">
          <video controls autoplay loop>
            <source src="videos/video.mp4" type="video/mp4">
            <source src="videos/video.ogg" type="video/ogg">
          Your browser does not support the video tag.
          </video>
        </section>
        
        <section class="cd-hero">

          <div class="cd-slider-nav">
            <nav>
              <span class="cd-marker item-1"></span>
              <ul>

              </ul>
            </nav> 
          </div> <!-- .cd-slider-nav -->

          <ul class="cd-hero-slider">

            <li class="selected">
              <div class="heading">
                <h1>Survey</h1>
                <span>Please respond the questionaire</span>
              </div>
              <div class="cd-full-width first-slide">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content first-content">
                        
                        <div id="questionaire"></div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>
            
            <li>
              <div class="heading">
                <h1>Upload</h1>
                <span>Upload the list of respondents</span> 
              </div>
              <div class="cd-half-width second-slide">   
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content second-content">
                        <div class="row">
                          <div class="col-md-7 left-image">
                            <img src="img/left-about-image.jpg">
                          </div>
                          <div class="col-md-5">
                            <div class="right-about-text">
                              <h4>Upload</h4>
                              <p>Fusce neque leo, dapibus sed imperdiet sed, vulputate sed purus. Nam eget justo in nibh facilisis rhoncus. Donec et risus non mauris lobortis convallis. Aliquam id urna quis ante blandit semper.</p>
                              <div class="primary-button">
                                <!-- <a href="#">Discover More</a> -->
                                <input class="tablinks" type="file" id="fileUploader" name="Upload" accept=".xls, .xlsx" onchange="upload(this)"/>
                                <button class="tablinks" type="button" id="uploadExcel" onclick="uploadClick()">Upload</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>
			
			<li>
              <div class="heading">
                <h1>Featured</h1>
                <span>We produce featured product every week</span> 
              </div>
              <div class="cd-half-width third-slide">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content third-content">
                      	
                      	<select id="surveys" onchange="show(this)">
                      		<option value="">-- Select --</option>
                      	</select>
                        <div class="row">
                       
                          <div class="col-md-5">
                          	
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>
            
            <li>
              <div class="heading">
                <h1>About Us</h1>
                <span>Get More Info About Our Agency</span> 
              </div>
              <div class="cd-half-width second-slide">   
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content second-content">
                        <div class="row">
                          <div class="col-md-7 left-image">
                            <img src="img/left-about-image.jpg">
                          </div>
                          <div class="col-md-5">
                            <div class="right-about-text">
                              <h4>Who we are?</h4>
                              <p>Fusce neque leo, dapibus sed imperdiet sed, vulputate sed purus. Nam eget justo in nibh facilisis rhoncus. Donec et risus non mauris lobortis convallis. Aliquam id urna quis ante blandit semper.</p>
                              <!-- <div class="primary-button">
                                <a href="#">Discover More</a>
                              </div> -->
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-7">
                            <div class="left-about-text">
                              <h4>What we do?</h4>
                              <p>Nam aliquam ultrices interdum. Vivamus metus mi, accumsan a tincidunt a, efficitur id felis. Vivamus non nibh malesuada, vestibulum nulla in, iaculis sem. Aenean tincidunt faucibus ipsum, ac aliquet nunc accumsan sed. Nulla sodales nunc sit amet libero egestas, ut interdum ex congue.</p>
                              <!-- <div class="primary-button">
                                <a href="#">Discover More</a>
                              </div> -->
                            </div>
                          </div>
                          <div class="col-md-5 right-image">
                            <img src="img/right-about-image.jpg">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>


            <li>
              <div class="heading">
                <h1>Our projects</h1>
                <span>Here you can check our recent projects</span> 
              </div>
              <div class="cd-half-width fourth-slide">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content fourth-content">
                        <div class="row">
                          <div class="col-md-3 project-item">
                            <a href="img/item-01.jpg" data-lightbox="image-1"><img src="img/project-item-01.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-02.jpg" data-lightbox="image-1"><img src="img/project-item-02.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-03.jpg" data-lightbox="image-1"><img src="img/project-item-03.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-04.jpg" data-lightbox="image-1"><img src="img/project-item-04.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-05.jpg" data-lightbox="image-1"><img src="img/project-item-05.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-06.jpg" data-lightbox="image-1"><img src="img/project-item-06.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-07.jpg" data-lightbox="image-1"><img src="img/project-item-07.jpg"></a>
                          </div>
                          <div class="col-md-3 project-item">
                            <a href="img/item-08.jpg" data-lightbox="image-1"><img src="img/project-item-08.jpg"></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>

            <li>
              <div class="heading">
                <h1>Contact us</h1>
                <span>You'll be responded within 48 hrs</span> 
              </div>
              <div class="cd-half-width fivth-slide">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="content fivth-content">
                        <div class="row">
                          <div class="col-md-4">
                            <div class="left-info">
                              <p>Maecenas imperdiet sagittis lacus, ut consequat velit iaculis id. Praesent eu consequat urna. Morbi justo dolor, ornare sed lorem et, auctor iaculis ligula.
                              <br><br>
                              <em>5566 Donec mollis libero<br>at metus luctus 10660</em>
                              </p>
                              <ul class="social-icons">
                                <i><a href="#"><i class="fa fa-facebook"></i></a></i>
                                <i><a href="#"><i class="fa fa-twitter"></i></a></i>
                                <i><a href="#"><i class="fa fa-linkedin"></i></a></i>
                                <i><a href="#"><i class="fa fa-rss"></i></a></i>
                                <i><a href="#"><i class="fa fa-behance"></i></a></i>
                              </ul>
                            </div>
                          </div>
                          <div class="col-md-8">
                            <div class="row">
                              <form id="contact" action="" method="post">
                                <div class="col-md-6">
                                  <fieldset>
                                    <input name="name" type="text" class="form-control" id="name" placeholder="Your Name" required="">
                                  </fieldset>
                                </div>
                                <div class="col-md-6">
                                  <fieldset>
                                    <input name="email" type="email" class="form-control" id="email" placeholder="Email" required="">
                                  </fieldset>
                                </div>
                                <div class="col-md-12">
                                  <fieldset>
                                    <textarea name="message" rows="6" class="form-control" id="message" placeholder="Message" required=""></textarea>
                                  </fieldset>
                                </div>
                                <div class="col-md-12">
                                  <fieldset>
                                    <button type="submit" id="form-submit" class="btn">Send Message</button>
                                  </fieldset>
                                </div>
                              </form>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>                  
                </div>
              </div>
            </li>
          </ul> <!-- .cd-hero-slider -->
        </section> <!-- .cd-hero -->


        <footer>
          <p>Copyright &copy; 2020 Your Company 
                                
        	| Designed by <em>Template Mo</em></p>
        </footer>
    
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>