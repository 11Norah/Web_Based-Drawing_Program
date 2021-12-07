import { isNull } from '@angular/compiler/src/output/output_ast';
import { CONTEXT_NAME } from '@angular/compiler/src/render3/view/util';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';


@Component({
  selector: 'app-shapes',
  templateUrl: './shapes.component.html',
  styleUrls: ['./shapes.component.css']
})
export class SHAPESComponent implements OnInit {


  constructor() {

  }
  ngOnInit(): void {

  }}
  export class rect{
    isDrawing: boolean;
    startX=0;
     startY=0;
     lastX=0;
     lastY=0;
    constructor(private context :CanvasRenderingContext2D){}

    rectangle(){
      this.isDrawing =false;
      let x=0;
      let y=0;
      let lasty=0;
      let lastx=0;
      onmousedown = (event: MouseEvent) => {
         x=event.clientX;
         y=event.clientY;
         onmousedown = (event: MouseEvent) => {
          lastx=event.clientX;
          lasty=event.clientY;
          console.log(x,y);
            if(!this.isDrawing){
             this.context.strokeRect(x,y,lastx-x,lasty-y);
             lastx=lasty=0;
             this.isDrawing=true;}
       }
      }
    }}
  /**private paint: boolean;

  private clickX: number[] = [];
  private clickY: number[] = [];
  private clickDrag: boolean[] = [];





 circle(event :any){
var x = event.clientX;
 var y = event.clientY;
 var offsetX = event.offsetX;
 var offsetY = event.offsetY;
 //alert(x, y, offsetX, offsetY);

 /// These are the 2 new lines, see you target the canvas element then apply it to getContext
 var canvasElement = <HTMLCanvasElement>document.getElementById("canvas");
 var ctx = canvasElement.getContext("2d");

  //draw a circle
  if(ctx){
  ctx.beginPath();
  ctx.arc(x, y, 10, 0, Math.PI*2, true);
  ctx.closePath();
  ctx.fill();}}


}*/
function getCanvasRenderingContext2D(arg0: any): CanvasRenderingContext2D {
  throw new Error('Function not implemented.');
}

function getCanvasElementById(arg0: string): any {
  throw new Error('Function not implemented.');
}





function rectangle() {
  throw new Error('Function not implemented.');
}
