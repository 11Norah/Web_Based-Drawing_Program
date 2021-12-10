import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {HttpClient ,HttpHeaders} from '@angular/common/http';
import {  SHAPES, SHAPESComponent } from './shapes/shapes.component';
import { ConfigurationService } from './configuration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'paint';
  
  @ViewChild('canvas',{static:true})

  canvas: ElementRef<HTMLCanvasElement> ;
  context :CanvasRenderingContext2D ;
  res:any
  constructor( private ShapeService :ConfigurationService ){}
  private hhtp:HttpClient
   ngOnInit():void {
    this.context=this.canvas.nativeElement.getContext('2d');
    
}
rectangle():void{
    
  const shape_rect=new SHAPES(this.context,this.ShapeService);
  shape_rect.rectangle();
};

circle():void{
  const shape_circle= new SHAPES(this.context,this.ShapeService);
  shape_circle.circle();
}
square():void{
  
  const shape_square=new SHAPES(this.context,this.ShapeService);
  
  shape_square.square();
 
};
  
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

      line(){
        const new_line=new SHAPES(this.context,this.ShapeService);
       
        new_line.drawLine();
       
      }    
      ellipse():void{
       const shape_ellipse= new SHAPES(this.context,this.ShapeService);
       
       shape_ellipse.ellipse();
     }
     triangle():void{
       const shape_triangle=new SHAPES(this.context,this.ShapeService);
       
       shape_triangle.triangle();
     }
 
  move(){
 
    let selectx=0;
    let selecty=0;
    let newx=0;
    let newy=0;
    onmousedown=(event:MouseEvent)=>{
      selectx=event.offsetX;
      selecty=event.offsetY;
      console.log(selectx,selecty)
      onmousedown=(event:MouseEvent)=>{
      newx=event.offsetX;
      newy=event.offsetY;
      console.log(newx,newy)
      this.res=this.ShapeService.move_send(selectx,selecty,newx,newy)
      this.load(this.res)
      
      }}
  }
  copy(){
    let selectx=0;
    let selecty=0;
    let newx=0;
    let newy=0;
    onmousedown=(event:MouseEvent)=>{
      selectx=event.offsetX;
      selecty=event.offsetY;
      console.log(selectx,selecty)
      onmousedown=(event:MouseEvent)=>{
      newx=event.offsetX;
      newy=event.offsetY;
      console.log(newx,newy)
    //  this.ShapeService.copy_send({x:selectx,y:selecty})
    }}
  }
  resize(){
    let selectx=0;
    let selecty=0;
    let newx=0;
    let newy=0;
    onmousedown=(event:MouseEvent)=>{
      selectx=event.offsetX;
      selecty=event.offsetY;
      console.log(selectx,selecty)
      onmousedown=(event:MouseEvent)=>{
      newx=event.offsetX;
      newy=event.offsetY;
      console.log(newx,newy)
      }
    }
  }


  load(shapes:{name:string,color:string,x1:number,y1:number,x2:number,y2:number,x3:number,y3:number}[]){
    let i=0;
    let findx1,findx2,findx3;
    let findy1,findy2,findy3;
    let findcolor,findtype;
    for( i=0;i<shapes.length;i++){
      findx1=shapes[i].x1,findx2=shapes[i].x2,findx3=shapes[i].x3,findcolor=shapes[i].color,findtype=shapes[i].name
      findy1=shapes[i].y1,findy2=shapes[i].y2,findy3=shapes[i].y3
      if(findtype=='rectangle'){
        this.context.strokeStyle=findcolor;
        this.context.strokeRect(findx1,findy1,findx2-findx1,findy2-findy1);
        console.log(shapes[i].color)
      }
      else if(findtype=='square'){
        this.context.strokeStyle=findcolor;
        this.context.strokeRect(findx1,findy1,findx2-findx1,findx2-findx1.x);
      }
      else if(findtype=='circle'){
        let radius=0
        radius=Math.sqrt(Math.pow((findx2-findx1),2)+Math.pow((findy2-findy1),2));
        this.context.beginPath();
        this.context.strokeStyle=findcolor
              this.context.arc(findx1,findy1,radius,0,2*Math.PI);
              this.context.stroke();
      }
      else if(findtype=='ellipse'){
        let radius1=0;
        let radius2=0;
        this.context.beginPath();
        radius1=Math.sqrt(Math.pow((shapes[i].x2-shapes[i].x1),2)+Math.pow((shapes[i].y2-shapes[i].y1),2));
        radius2=Math.sqrt(Math.pow((shapes[i].x3-shapes[i].x1),2)+Math.pow((shapes[i].y3-shapes[i].y1),2));
        console.log(radius1);
        console.log(radius2);
        this.context.beginPath();
            this.context.ellipse(shapes[i].x1,shapes[i].y1,radius1,radius2,Math.PI,0,2*Math.PI);
            this.context.stroke();
      }
      else if(findtype=='triangle'){
        this.context.beginPath();
        this.context.strokeStyle=findcolor
        this.context.moveTo(findx1,findy1);
        this.context.lineTo(findx2,findy2);
        this.context.lineTo(findx3,findy3);
        this.context.moveTo(findx3,findy3);
        this.context.lineTo(findx1,findy1);
        this.context.stroke();
      }
      else if(findtype='line'){
        this.context.beginPath();
        this.context.strokeStyle=findcolor;
        this.context.moveTo(findx1, findy1);   
      this.context.lineTo(findx2, findy2);
      this.context.lineWidth = 2;
      this.context.stroke();
      this.context.closePath();
      }
    }
  }
  color(obj) { 
    var x,y;
    this.context.strokeStyle=obj;
    onmousedown=(event:MouseEvent)=>{
      x=event.offsetX;
      y=event.offsetY;
    }
   

}}
 


/*window.addEventListener("load", ()=>{
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


