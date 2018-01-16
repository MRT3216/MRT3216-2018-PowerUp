let ux = {
    startMatch: {
    	startButton: document.getElementById('startTimer'), // ux.startMatch.startButton
    	isRunning: false,
    },
    resetMatch: {
      resetButton: document.getElementById('resetTimer'),
    },
    colorRed: {
      red: document.getElementById('redButton'),
    },
    colorBlue:{
      blue: document.getElementById('blueButton'),
    },
    colors:{
    	all: document.getElementById('center'),
    }
}
var topArrow = document.getElementById("speedArrowsTop");
topArrow.setAttribute("style", "display: none;");
var middleArrow = document.getElementById("speedArrowsMiddle");
middleArrow.setAttribute("style", "display: none;");
var bottomArrow = document.getElementById("speedArrowsBottom");
bottomArrow.setAttribute("style", "display: none;");
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
}

// JS code for timer
document.getElementById('timer').innerHTML =
  02 + ":" + 30;
// JS Code for match start
ux.startMatch.startButton.onclick = onStartClickHandler;

function onStartClickHandler() {
	if( ux.startMatch.isRunning ) {
		return;
	}

	startTimer();
}

function startTimer(){
	var presentTime = document.getElementById('timer').innerHTML;
  	var timeArray = presentTime.split(/[:]+/);
  	var m = timeArray[0];
  	var s = checkSecond((timeArray[1] - 1));
  	if(s==59){m=m-1}
  	//if(m<0){alert('timer completed')}
  	if(m<1 && s<1){alert("times up!")}
  	if(m<1 && s<1){document.getElementById('timer').innerHTML = 02 + ":" + 30;};
  	if(m<1 && s<1){ux.startMatch.isRunning= false;}
  	if(m<1 && s<1){return};


  	//if(0 || 1){return};
  	//if(m && s){return};
  	document.getElementById('timer').innerHTML =
    	m + ":" + s;
  	setTimeout(startTimer, 1000);
  	ux.startMatch.isRunning=true;
}

function checkSecond(sec) {
  if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
  if (sec < 0) {sec = "59"};
  return sec;
}

ux.colorRed.red.onclick = function changeColor(){
  var red1 = document.getElementById("canChange");
  var red2 = document.getElementById("canChange2");
  red1.setAttribute("style", "color: red;");
  red2.setAttribute("style", "color: red;");

}
ux.colorBlue.blue.onclick = function changeColor(){
  var blue1 = document.getElementById("canChange");
  var blue2 = document.getElementById("canChange2");
  blue1.setAttribute("style", "color: blue;");
  blue2.setAttribute("style", "color: blue;");
}

ux.colors.all.onchange = function changeColors(){
  // var elements = document.getElementById("canChange");
  // var elements2 = document.getElementById("canChange2");
  // color1 = document.getElementById("canChange").value;
  // color2 = document.getElementById("canChange2").value;
  // elements.setAttribute("style", "color: " + color1 +" ;");
  // elements2.setAttribute("style", "color: " + color2+" ;");
  console.log("lol")

}