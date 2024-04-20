
document.getElementById("showFormLink").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default link behavior
    var form1 = document.getElementById("bookingForm");
    var form3 = document.getElementById("showHome");
    
    if (form1.style.display === "none") {
        form1.style.display = "block"; // Show the form
        form3.style.display = "none";
    }
}); 


document.getElementById("showHomeDetails").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default link behavior
    var form1 = document.getElementById("bookingForm");
    var form3 = document.getElementById("showHome");
    
    if (form3.style.display === "none") {
        form3.style.display = "block"; // Show the form
        form1.style.display = "none";
    }
});


const navLinks = document.querySelectorAll('.nav-link');

navLinks.forEach(link => {
link.addEventListener('click', function (event) {
    // Remove the 'active' class from all links
    navLinks.forEach(link => link.classList.remove('active'));

    // Add the 'active' class to the clicked link
    this.classList.add('active');
    console.log(this.classList);
  });
});