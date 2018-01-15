let ux = {
    slider: {
        button: document.getElementById('foo')
    },
    startMatch: {
    	startButton: document.getElementById('startTimer')
    },
    resetMatch: {
      resetButton: document.getElementById('resetTimer')
    }
}
var topArrow = document.getElementById("speedArrowsTop");
topArrow.setAttribute("style", "display: none;");
var middleArrow = document.getElementById("speedArrowsMiddle");
middleArrow.setAttribute("style", "display: none;");
var bottomArrow = document.getElementById("speedArrowsBottom");
bottomArrow.setAttribute("style", "display: none;");
var single = document.getElementById("expRect1");
single.setAttribute("style", "display: none;");
var local1 = document.getElementById("expect1");
local1.setAttribute("style", "display: none;");
var local2 = document.getElementById("expect2");
local3.setAttribute("style", "display: none;");
var local3 = document.getElementById("expect3");
local3.setAttribute("style", "display: none;");
var secon = document.getElementById("expRect2");
secon.setAttribute("style", "display: none;");
var tres = document.getElementById("expRect3");
tres.setAttribute("style", "display: none;");

// My event handler
// ux.foo.button.onclick = function foo(event) {
//     console.log(event); 
//     console.log(event.target);
// }

// console.log(ux.foo.button)

// ux.foo.button.addEventListener("mouseover",outputUpdate)

// ux.bar.slider.addEventListener("change",function bar(event) {
//     console.log(event);
// })

// function for speed change
function outputUpdate(speed) {
    console.log(speed)
    document.querySelector('#speed').value = speed;
    if(speed > 75){
      topArrow.setAttribute("style", "display: inline;");
    }
    else{
      topArrow.setAttribute("style", "display: none;");
    }
    if(speed > 50){
      middleArrow.setAttribute("style", "display: inline;");
    }
    else{
      middleArrow.setAttribute("style", "display: none;");
    }
    if(speed > 25){
      bottomArrow.setAttribute("style", "display: inline;");
    }
    else{
      bottomArrow.setAttribute("style", "display: none;");
    }
}

// function for sensitivity change
function outputUpdate2(sensitivity) {
    console.log(sensitivity)
    document.querySelector('#sensitivity').value = sensitivity;
}

// function for deadzone change
function outputUpdate3(deadzone) {
    console.log(deadzone)
    document.querySelector('#deadzone').value = deadzone;
}
function outputUpdate4(armHeight) {
    console.log(armHeight)
    document.querySelector('#armHeight').value = armHeight;
	if(armHeight > 25){
		single.setAttribute("style", "display: inline;");
		local1.setAttribute("style", "display: inline;");
	}
	else{
		single.setAttribute("style", "display: none;");
		local1.setAttribute("style", "display: none;");
	if(armHeight > 50 {
		secon.setAttribute("style", "display: inline;");
		local2.setAttribute("style", "display: inline;");
	}
	else{
		secon.setAttribute("style", "display: none;");
		local2.setAttribute("style", "display: none;");
	}
	if(armHeight > 75){
		tres.setAttribute("style", "display: inline;");
		local3.setAttribute("style", "display: inline;");
	}
	else{
		tres.setAttribute("style", "display: none;");
		local3.setAttribute("style", "display: none;");
	}
}


// JS code for timer
document.getElementById('timer').innerHTML =
  02 + ":" + 30;

// JS Code for match start
ux.startMatch.startButton.onclick = function startTimer(){
  var presentTime = document.getElementById('timer').innerHTML;
  var timeArray = presentTime.split(/[:]+/);
  var m = timeArray[0];
  var s = checkSecond((timeArray[1] - 1));
  if(s==59){m=m-1}
  //if(m<0){alert('timer completed')}
  if(m<1 && s<1){alert("times up!")};
  document.getElementById('timer').innerHTML =
  00 + ":" + 00;
  if(m<1 && s<1){document.getElementById('timer').innerHTML = 02 + ":" + 30;};
  if(m<1 && s<1){return};
  document.getElementById('timer').innerHTML =
    m + ":" + s;
  setTimeout(startTimer, 1000);
}

function checkSecond(sec) {
  if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
  if (sec < 0) {sec = "59"};
  return sec;
}





