import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import {  SHAPES, SHAPESComponent } from './shapes/shapes.component';

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
  
   ngOnInit():void {
    this.context=this.canvas.nativeElement.getContext('2d');
}
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
    if (m) {
        this.context.clearRect(0, 0, 1000, 500);}}
}



