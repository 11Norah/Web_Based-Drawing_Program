import { isNull } from '@angular/compiler/src/output/output_ast';
import { CONTEXT_NAME } from '@angular/compiler/src/render3/view/util';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfigurationService } from '../configuration.service';


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
    colourchanged: boolean= false;
    startX: number;
    startY: number;
    lastX: number;
    lastY: number;
   
    constructor(private context :CanvasRenderingContext2D, private SEND: ConfigurationService ){}
    //newcolour:string="black";
   hexcolours() :string {
     let object=this.context.strokeStyle;
     console.log(object);
     let x:string;
    switch (object) {
      case "#008000":
          x = "green";
          break;
      case "#0000ff":
          x = "blue";
          break;
      case "#ff0000":
          x = "red";
          break;
      case "#ffff00":
          x = "yellow";
          break;
      case "#ffa500":
          x = "orange";
          break;
      case "#000000":
          x = "black";
          break;
      case "#ffffff":
          x = "white";
          break;
  }return(x);
   }
  
    rectangle(){
    
      let x=0;
      let y=0;
      let lasty=0;
      let lastx=0;
      
      onmousedown = (event: MouseEvent) => {
         x=event.offsetX;
         y=event.offsetY;
         onmousedown = (event: MouseEvent) => {
          lastx=event.offsetX;
          lasty=event.offsetY;
          console.log(x,y);
          this.context.beginPath();
          console.log(this.colourchanged);
          
            let color =this.hexcolours();
            this.context.strokeRect(x,y,lastx-x,lasty-y);
            this.SEND.send({x1:x,y1:y},{x2:lastx,y2:lasty},"rectangle",color);
            this.rectangle();
            this.context.beginPath();
       }
      }
      
    } 
    ellipse(){
     
      let x=0;
      let y=0;
      let lasty=0;
      let lastx=0;
      let radiousx=0;
      let radiousy=0;
      let x2=0;
      let y2=0;
      onmousedown = (event: MouseEvent) => { 
         x=event.offsetX;
         y=event.offsetY;
         onmousedown = (event: MouseEvent) => { 
          lastx=event.offsetX;
          lasty=event.offsetY; 
          onmousedown=(event:MouseEvent)=>{
            x2=event.offsetX;
            y2=event.offsetY;
            let color =this.hexcolours();
          console.log(x,y);
          console.log(x2,y2);
          radiousx=Math.sqrt(Math.pow((lastx-x),2)+Math.pow((lasty-y),2));
          radiousy=Math.sqrt(Math.pow((x2-x),2)+Math.pow((y2-y),2));
          console.log(radiousx);
          console.log(radiousy);
          this.context.beginPath();
              this.context.ellipse(x,y,radiousx,radiousy,Math.PI,0,2*Math.PI);
              this.context.stroke();
              this.SEND.send_et({x1:x,y1:y},{x2:lastx,y2:lasty},{x3:x2,y3:y2},'ellipse',color);
              this.ellipse();
              this.context.closePath();
              

    }
  }
  }}
    
    circle(){
     
      let x=0;
      let y=0;
      let lasty=0;
      let lastx=0;
      let radious=0;
      let m1=0;
      let m2=0;
      onmousedown = (event: MouseEvent) => { 
         x=event.offsetX;
         y=event.offsetY;
         onmousedown = (event: MouseEvent) => { 
          lastx=event.offsetX;
          lasty=event.offsetY; 
          
          console.log(x,y);
          radious=Math.sqrt(Math.pow((lastx-x),2)+Math.pow((lasty-y),2));
          console.log(radious);
          this.context.beginPath();
          let color =this.hexcolours();
              this.context.arc(x,y,radious,0,2*Math.PI);
              this.context.stroke();
              this.SEND.send({x1:x,y1:y},{x2:lastx,y2:lasty},'circle',color);
              this.circle();
              this.context.beginPath();
     
    } 
    }}
   square(){
     
    
    let x=0;
    let y=0;
    let lasty=0;
    let lastx=0;
    onmousedown = (event: MouseEvent) => { 
       x=event.offsetX;
       y=event.offsetY;
       onmousedown = (event: MouseEvent) => { 
        lastx=event.offsetX;
        lasty=event.offsetY; 
        console.log(x,y);
        this.context.beginPath();
        let color =this.hexcolours();
           this.context.strokeRect(x,y,lastx-x,lastx-x);
           this.context.strokeStyle="black";
           this.SEND.send({x1:x,y1:y},{x2:lastx,y2:lasty},'square',color);
           this.square();
           this.context.beginPath();
     }
    } 
   
   }
    drawLine() {
    let prevX=0
    let prevY=0;
    let currX=0;
    let currY=0;
    console.log("ana hna");
    onmousedown=(event:MouseEvent)=>{ prevX=event.offsetX; prevY=event.offsetY;
    console.log(prevX,prevY);
    onmousedown=(ev :MouseEvent)=>{ currX=ev.offsetX; currY=ev.offsetY;
      console.log(prevX,currX,currY);
      this.context.beginPath();
      this.context.moveTo(prevX, prevY);   
    this.context.lineTo(currX, currY);
    let color =this.hexcolours();
    this.context.lineWidth = 2;
    this.context.stroke();
    this.context.closePath();
    this.SEND.send({x1:prevX,y1:prevY},{x2:currX,y2:currY},'line',color);
    this.drawLine();
    
    this.context.beginPath();
   }}
  
}
triangle(){
  
  let x=0;
  let y=0;
  let lasty=0;
  let lastx=0;
  let radiousx=0;
  let radiousy=0;
  let x2=0;
  let y2=0;
  onmousedown = (event: MouseEvent) => { 
     x=event.offsetX;
     y=event.offsetY;
     onmousedown = (event: MouseEvent) => { 
      lastx=event.offsetX;
      lasty=event.offsetY; 
      onmousedown=(event:MouseEvent)=>{
        x2=event.offsetX;
        y2=event.offsetY;
        let color =this.hexcolours();
      
      this.context.beginPath();
          this.context.moveTo(x,y);
          this.context.lineTo(lastx,lasty);
          this.context.lineTo(x2,y2);
          this.context.moveTo(x2,y2);
          this.context.lineTo(x,y);
          this.context.stroke();
          this.SEND.send_et({x1:x,y1:y},{x2:lastx,y2:lasty},{x3:x2,y3:y2},'triangle',color);
          this.triangle();
          this.context.closePath();
         

}
}
}
}

  }
