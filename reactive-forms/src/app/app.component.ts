import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validator, Validators, FormGroup, EmailValidator, FormArray } from '@angular/forms';
import { forbiddenNameValidator } from './shared/validator';
import { passwordValidator } from './shared/password.validator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  registrationForm: FormGroup;

  get username(){
    return this.registrationForm.get('username');
  }

  get email(){
    return this.registrationForm.get('email');
  }
  get alternateEmails(){
    return this.registrationForm.get('alternateEmails') as FormArray
  }

  addAlternateEmails(){
    this.alternateEmails.push(this.fb.control(''));
  }
  constructor(private fb: FormBuilder){}
  
  ngOnInit(): void {
    this.registrationForm=this.fb.group({
      username: ['Gull', [Validators.required, Validators.minLength(3), forbiddenNameValidator(/password/)]],
      password: [''],
      email:[''],
      subscribe: [false],
      confirmPassword: [''],
      address: this.fb.group({
        city: [''],
        state: [''],
        postalCode: ['']
      }),
      alternateEmails: this.fb.array([])
    }, {validator: passwordValidator});

    this.registrationForm.get('subscribe').valueChanges
      .subscribe(checkedvalue=>{
        if(checkedvalue){
          this.email.setValidators(Validators.required);
        }else{
          this.email.clearValidators();
        }
        this.email.updateValueAndValidity();
      })
  }  


  
  // registrationForm=new FormGroup({
  //   username: new FormControl('Gull'),
  //   password: new FormControl(''),
  //   confirmPassword: new FormControl(''),
  //   address: new FormGroup({
  //     city: new FormControl(''),
  //     state: new FormControl(''),
  //     postalCode: new FormControl('')
  //   })
  // })
  loadApiData(){
    this.registrationForm.patchValue({
      username:'Gull',
      password:'123',
      confirmPassword:'123',      
    })
  }
}
