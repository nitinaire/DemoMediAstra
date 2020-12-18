import { EmployeeService } from '../employee.service';
import { HelpingHand } from '../helpinghand'; 
import { ResponseMessage } from '../responsemessage'; 
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'; 
import { Router } from '@angular/router';  
@Component({
  selector: 'app-helping-hand',
  templateUrl: './helping-hand.component.html',
  styleUrls: ['./helping-hand.component.css']
})
export class HelpingHandComponent implements OnInit {
	
	selectedFiles: FileList;  
  currentFileUpload: File; 
helpinghand: HelpingHand = new HelpingHand();
responseMessage: ResponseMessage = new ResponseMessage();
 constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
  }
  form = new FormGroup({  
    name : new FormControl('' , Validators.required),  
    reason : new FormControl('' , Validators.required)
  });
selectFile(event) {  
    const file = event.target.files.item(0);  
   
   
      var size = event.target.files[0].size;  
      if(size > 1000000)  
      {  
          alert("size must not exceeds 1 MB");    
      }  
      else  
      {  
        this.selectedFiles = event.target.files;  
      }  
      
  }
onUpload(){
	 if(this.selectedFiles != null)  {
  
    this.employeeService
    .createHelpingrequest(this.helpinghand).subscribe(  
      response => {  
         this.helpinghand = response;
          console.log(this.helpinghand); 
          if(this.helpinghand.name !='')  
          {  
             
              this.currentFileUpload = this.selectedFiles.item(0);  
              console.log(this.currentFileUpload);  
  
              this.employeeService.uploadFile(this.currentFileUpload , this.helpinghand.id).subscribe(  
                  res => {  
  
                    this.responseMessage= res;  
                     if(this.responseMessage.message.includes("Uploaded the file successfully"))  
                     {  
                        alert("Request Created successfully ");  
						this.gotoList()
                     }  
                     else{  
                        alert("error while uploading fie details");  
                     }  
                  },  
                  err => {  
                      alert("error while uploading fie details");  
                  }  
              );  
  
              
          }  
      },  
      error => {  
        console.log("error while saving data in the DB");  
      }  
    ); 
}else{
alert("please uploas file");
}	
  
} 
gotoList() {
    this.router.navigate(['/helpinghandrequestList']);
  }
}
