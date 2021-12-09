import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { SHAPES, SHAPESComponent } from './shapes/shapes.component';
import{ShapeService}from './shape.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'paint';
  @ViewChild('canvas',{static:true})

  canvas: ElementRef<HTMLCanvasElement> ;
   private  context :CanvasRenderingContext2D ;
   private ShapeService: ShapeService;
   flag=0;
  
   ngOnInit():void {
    this.context=this.canvas.nativeElement.getContext('2d');
}
private colorchanged:string;

  rectangle():void{
    const shape_rect=new SHAPES(this.context);
    shape_rect.rectangle();
  };
  square():void{
    
    const shape_square=new SHAPES(this.context);
    shape_square.square();
  };
  line(){
    const new_line=new SHAPES(this.context);
    new_line.drawLine();
  }  
  clearCanvas() {
    var m = confirm("Want to clear");
    var x,y;
    if (m) {
        this.context.clearRect(0, 0, this.context.canvas.width, this.context.canvas.height);
        onmousedown=(event:MouseEvent)=>{
          x=event.offsetX;
          y=event.offsetY;
        }
      }
      }

  circle():void{
    const shape_circle= new SHAPES(this.context);
    shape_circle.circle();
  }
  ellipse():void{
    const shape_ellipse= new SHAPES(this.context);
    shape_ellipse.ellipse();
  }
  triangle():void{
    const shape_triangle=new SHAPES(this.context);
    shape_triangle.triangle();
  }
  color(obj) { 
    var x,y;
    this.context.strokeStyle=obj;
    onmousedown=(event:MouseEvent)=>{
      x=event.offsetX;
      y=event.offsetY;
    }
   

}}
 




