import React, { useState, useEffect } from "react";
import Logo from "../assets/logo.png";
import "../styles/Login.css";

import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBInput,
  MDBIcon,
  MDBValidationItem,
  MDBValidation,
} from "mdb-react-ui-kit";

function Signup1() {
  var varflag = true;
  const [valflag, setValflag] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPass, setConfirmPass] = useState("");
  const [flag, setFlag] = useState(false);
  const [v, setV] = useState(false);
  const [FirstNameErrorMessage, setFirstNameErrorMessage] = useState("");
  const [lastNameErrorMessage, setLastNameErrorMessage] = useState("");
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setsuccessMessage] = useState("");
  const [confirmMessage, setConfirmMessage] = useState("");
  var signupDto = { firstName, lastName, email, password };
  useEffect(() => {
    console.log("use");
    console.log(valflag);
  });

  const regEx = /[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,8}(.[a-z{2,8}])?/g;

  const signUpRequestFirstName = (e) => {
    e.preventDefault();
    setFirstNameErrorMessage("");
    setFirstName(e.target.value);
	varflag = false;
  };
  const signUpRequestLastName = (e) => {
    setV(true);
    console.log(v);
    e.preventDefault();
    setLastNameErrorMessage("");
    setLastName(e.target.value);
	varflag = false;
  };
  const signUpRequestEmail = (e) => {
    e.preventDefault();
	varflag = false;
    let testEmail = e.target.value;

    if (regEx.test(testEmail)) {
      setEmail(testEmail);
      setEmailErrorMessage("");
    } else if (testEmail == "") {
      // setFlag()
      setEmail("");
      setEmailErrorMessage("");
    } else {
      setEmail("");
      setEmailErrorMessage("Invalid Email");
    }
  };

  const signUp = async (e) => {
    if (
      firstName!="" &&
      lastName != "" &&
      email != "" &&
      password != "" &&
      password === confirmPass
    ) {
      setFlag(true);
    } else {
      if(firstName == ""){
      setFirstName("")
      setFirstNameErrorMessage("First Name Cannot be Empty")
      }
      if (lastName == "") {
        setLastName("");
        setLastNameErrorMessage("Last Name Cannot be Empty");
      }
    }
    if (flag) {
      console.log(signupDto);
    }
    // const responseData = await axios.post(
    // 	"http://10.0.61.96:9000/user/add",
    // 	connectionDto
    // );
    // if(responseData.data.status){

    // 	sessionStorage.setItem("userId", responseData.data.data.userId);
    // 	navigate("/dashboard");
    // }
    // else{
    // 	alert("Invalid Credentials")
    // }
    // console.log(responseData.data);

    // if (responseData.data.data != null) {
    // 	navigate("/dashboard");
    // }
  };
  const confirmPassword = (e) => {
    // e.preventDefault();
    let con_pass = e.target.value;
	varflag = false;
    console.log("Con Pas ", con_pass);
    if (con_pass === password) {
      setConfirmPass(con_pass);
      setConfirmMessage("");
    } else {
      setConfirmMessage("Password does not match.");
    }
    if (con_pass == "") {
      setConfirmMessage("");
    }
    // 	setPassword(e.target.value);
    // 	console.log(password);
  };
  const handlePassword = (event) => {
    let new_pass = event.target.value;
    console.log("pass ", new_pass);

    console.log(errorMessage);
    // regular expressions to validate password
    var lowerCase = /[a-z]/g;
    var upperCase = /[A-Z]/g;
    var numbers = /[0-9]/g;
    if (new_pass == "") {
      setErrorMessage("");
      setsuccessMessage("");
    } else if (!new_pass.match(lowerCase)) {
      setErrorMessage("Password should contains lowercase letters!");
      setsuccessMessage("");
    } else if (!new_pass.match(upperCase)) {
      setErrorMessage("Password should contain uppercase letters!");
    } else if (!new_pass.match(numbers)) {
      setErrorMessage("Password should contains numbers also!");
      setsuccessMessage("");
    } else if (new_pass.length < 8) {
      setErrorMessage("Password length should be more than 8.");
      setsuccessMessage("");
    } else {
      setPassword(new_pass);
      setErrorMessage("");
      setsuccessMessage("Strong Password.. Go for it..");
    }
  };

  const valflagReset = () => {
   varflag = false;
  };

  return (
    <MDBContainer className="my-5 gradient-form overflow-hidden">
      <MDBRow>
        <MDBCol md="6" className="position-relative">
          <div
            id="radius-shape-1"
            className="position-absolute rounded-circle shadow-5-strong"
          ></div>
          <div
            id="radius-shape-2"
            className="position-absolute shadow-5-strong"
          ></div>

          <div className="text-center">
            <img src={Logo} style={{ width: "185px" }} alt="logo" />
            <h4 className="mt-1 mb-2 pb-1">
              We are The DBA Administration Team
            </h4>
          </div>
          <MDBCard className="my-5 bg-glass vh-80 mt-0 d-flex justify-content-center shadow-none">
            <MDBCardBody className="p-5">
              <MDBRow>
                <MDBCol col="6">
                  <span style={{ fontSize: 12, color: "red" }}>
                    
                    {FirstNameErrorMessage}
                  </span>
                </MDBCol>
                <MDBCol col="6">
                  <span style={{ fontSize: 12, color: "red" }}>
                    
                    {lastNameErrorMessage}
                  </span>
                </MDBCol>
              </MDBRow>
        
                  <MDBRow>
                    <MDBCol col="6">
                      <MDBValidation >
                        <MDBValidationItem >
                      <MDBInput
                        wrapperClass="mb-4"
                        label="First name"
                        id="form1"
                        type="text"
                        
                        
                        onChange={signUpRequestFirstName}
                      />
                      </MDBValidationItem>
                      </MDBValidation>
                    </MDBCol>

                    <MDBCol col="6">
                
                        <MDBInput
                          wrapperClass="mb-4"
                          label="Last name"
                          id="form2"
                          type="text"
                          required
                          onChange={signUpRequestLastName}
                        />
                     
                    </MDBCol>
                  </MDBRow>

                  <span style = {{ fontSize: 12, color: "red"   }}> {emailErrorMessage}</span>
              
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Email"
                      id="form3"
                      type="email"
                      required
                      onChange={signUpRequestEmail}
                    />
                 
                  <span style = {{ fontSize: 12, color: errorMessage? "red" : "green" }}> {errorMessage? errorMessage:successMessage}</span>
                  
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Password"
                      id="form4"
                      type="password"
                      required
                      onChange={handlePassword}
                    />
                  
              <span style={{ fontSize: 12, color: "red" }}>
                {" "}
                {confirmMessage}
              </span>

              <MDBInput
                wrapperClass="mb-4"
                label="Confirm password"
                id="form4"
                type="password"
                required
                onChange={confirmPassword}
              />

              <MDBBtn
                className="mb-4 w-100 gradient-custom-2 "
                onClick={signUp}
                size="md"
              >
                sign up
              </MDBBtn>

              <div className="text-center">
                <p>or sign up with:</p>

                <MDBBtn tag="a" color="none" className="mx-5">
                  <MDBIcon fab icon="facebook-f" size="2x" />
                </MDBBtn>

                <MDBBtn tag="a" color="none" className="mx-5">
                  <MDBIcon fab icon="twitter" size="2x" />
                </MDBBtn>

                <MDBBtn tag="a" color="none" className="mx-5">
                  <MDBIcon fab icon="google" size="2x" />
                </MDBBtn>

                <MDBBtn tag="a" color="none" className="mx-5">
                  <MDBIcon fab icon="github" size="2x" />
                </MDBBtn>
              </div>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
        <MDBCol
          md="6"
          className="container-fluid d-flex flex-column  justify-content-center gradient-custom-2 h-100 mb-4 text-black vh-80 mt-0 overflow-auto
        d-flex flex-column  justify-content-center gradient-custom-2 h-100 mb-4 text-black "
        >
          <h1 className="my-5 display-3  ls-tight px-2">
            We are not different, <br />
            <span>just a regular.....</span>
          </h1>
          <p className="px-3">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus
            deleniti ad quia saepe aliquid illum voluptatum mollitia error
            similique dolor. Quas, iure! Assumenda voluptatum asperiores odio
            quaerat, perferendis excepturi. Placeat, fugit obcaecati! Dolor
            consequatur ipsam nostrum sed molestias. Nam eum molestiae quasi
            porro eos sed vitae aliquam odio id quos maiores beatae placeat
            obcaecati ratione ducimus doloremque illo soluta quidem
            necessitatibus numquam accusamus, quia voluptatem magni. Explicabo
            rerum numquam eum sapiente dolore voluptate minima dicta. Natus
            inventore ullam molestiae. Voluptates soluta consequuntur velit quam
            repellat, veritatis qui adipisci nostrum beatae delectus at.
            Accusamus accusantium est labore, aliquam eligendi consequatur
            perspiciatis numquam consequuntur error consectetur inventore, optio
            officiis nihil porro! Quidem quis quo quam quae temporibus error
            eaque, ipsum nihil eius unde eos magni nostrum!
          </p>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}

export default Signup1;
