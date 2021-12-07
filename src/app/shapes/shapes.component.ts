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
  export class SHAPES{
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
            this.context.strokeRect(x,y,lastx-x,lasty-y);
             this.rectangle();
       }
      }
    } 
    square(){
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
            this.context.strokeRect(x,y,lastx-x,lastx-x);
           this.square();
     }
    } 
    
   }
    drawLine() {
    let prevX=0
    let prevY=0;
    let currX=0;
    let currY=0;
    console.log("ana hna");
    onmousedown=(event:MouseEvent)=>{ prevX=event.clientX; prevY=event.clientY;
    console.log(prevX,prevY);
    onmousedown=(ev :MouseEvent)=>{ currX=ev.clientX; currY=ev.clientY;
      console.log(prevX,currX,currY);
      this.context.beginPath();
      this.context.moveTo(prevX, prevY);   
    this.context.lineTo(currX, currY);
    
    this.context.strokeStyle = "black";
    this.context.lineWidth = 2;
    this.context.stroke();
    this.context.closePath();
    this.drawLine();
   }}
    }
  }
  



function getCanvasRenderingContext2D(arg0: any): CanvasRenderingContext2D {
  throw new Error('Function not implemented.');
}

function getCanvasElementById(arg0: string): any {
  throw new Error('Function not implemented.');
}





function rectangle() {
  throw new Error('Function not implemented.');
}
