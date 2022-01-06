
//------------------------- 드래그앤 드롭
    let desktop = document.getElementsByTagName("section")
    let mvnBox = document.getElementsByClassName("movingBox");
    for (let i = 1; i < mvnBox.length + 1; i++) {
        let mvnBoxId = document.getElementById("test0" + i);
        mvnBoxId.onmousedown = function (event) {

            let shiftX = event.clientX - mvnBoxId.getBoundingClientRect().left;
            let shiftY = event.clientY - mvnBoxId.getBoundingClientRect().top;

            mvnBoxId.style.position = "absolute";
            mvnBoxId.style.zIndex = 1000;
            document.body.appendChild(mvnBoxId);

            moveAt(event.pageX, event.pageY);

            function moveAt(pageX, pageY) {

                mvnBoxId.style.left = pageX - shiftX + "px";
                mvnBoxId.style.top = pageY - shiftY + "px";
            }

            function onMouseMove(event) {

                moveAt(event.pageX, event.pageY);

            }

            document.addEventListener("mousemove", onMouseMove);

            mvnBoxId.onmouseup = function () {

                document.removeEventListener("mousemove", onMouseMove);

                mvnBoxId.onmouseup = null;

            };


            mvnBoxId.ondragstart = function (e) {
                console.log("ondrastart", e)
                return false;


            };
        }

    }

    //데스크탑 우클릭
    let desktop = document.getElementsByTagName("section").item(0);

    function desktopRightClick(e) {
        console.log("BAB"   );
        if (typeof e === 'object') {
            switch (e.button) {
                /*
                                case 0 :
                                    alert('left button ');
                                    break;
                */

                /*
                                case 1 :
                                    alert('middle button ');
                                    break;
                */

                case 2 :
                    alert('right button ');



                    break;
                /*                default :
                                    alert('unknown Button');*/
            }
        }
    }

    desktop.addEventListener('mousedown',desktopRightClick);
