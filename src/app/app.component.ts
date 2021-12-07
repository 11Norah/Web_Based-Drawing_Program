import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { rect, SHAPESComponent } from './shapes/shapes.component';

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
  clearCanvas() {
    var m = confirm("Want to clear");
    if (m) {
        this.context.clearRect(0, 0, 1000, 500);}}
}

/**window.addEventListener("load", ()=>{
   let canvas =<HTMLCanvasElement> document.getElementById('canvas');
   
   let context =canvas.getContext('2d');
   let painting =false;
   
   function pressEventHandler(){
     painting=true;
   }
   function releaseEventHandler(){
     painting=false;
   }
   function dragEventHandler(e :any){
      if(!painting) return;
      if(context){
      context.lineWidth= 5;
      context.lineCap='round';
      context.lineTo(e.clientX ,e.clientY);
      context.stroke();
      
      }
   }
   canvas.addEventListener("mousedown", pressEventHandler);
    canvas.addEventListener("mousemove", dragEventHandler);
    canvas.addEventListener("mouseup", releaseEventHandler);
    
    
    
});*/

