package com.example.ksp.data.util

import com.example.ksp.data.model.ValidationResult

object Utils {

    fun validateLoginRequest(phone: String, password: String) : ValidationResult{
        if(phone.isBlank() && password.isBlank()) return ValidationResult(false, "Phone number and password cannot be blank")
        if(phone.isBlank()) return ValidationResult(false, "Phone number cannot be blank")
        if(password.isBlank()) return ValidationResult(false, "Password cannot be blank")
        return ValidationResult(true)
    }

    fun validateRegisterRequest(
        fullname: String,
        phone: String,
        dob: String,
        ic: Int,
        email: String,
        password1: String,
        password2: String
    ) : ValidationResult {
        if(fullname.isBlank() && phone.isBlank() && dob.isBlank() && ic===0 && email.isBlank() && password1.isBlank() && password2.isBlank())
            return ValidationResult(false, "All fields cannot be blank")
        if(fullname.isBlank()) return ValidationResult(false, "Please enter your full name")
        if(phone.isBlank()) return ValidationResult(false, "Phone number cannot be blank")
        if(dob.isBlank()) return ValidationResult(false, "Please enter your date of birth")
        if(ic===0) return ValidationResult(false, "Please enter your last four digit of ic")
        if(email.isBlank()) return ValidationResult(false, "Please enter your email")
        if(password1.isBlank() || password2.isBlank()) return ValidationResult(false, "Password cannot be blank")
        if(!password1.equals(password2)) return ValidationResult(false, "Password confirmation is not same previous password")
        return ValidationResult(true)
    }

    fun validateVerifyRequest(
        phone: String,
        dob: String,
        ic: Int
    ) : ValidationResult {
        if (phone.isBlank() && dob.isBlank() && ic===0)
            return ValidationResult(false, "All fields cannot be blank")
        if (phone.isBlank()) return ValidationResult(false, "Please enter your phone number")
        if(dob.isBlank()) return ValidationResult(false, "Please enter your date of birth")
        if(ic===0) return ValidationResult(false, "Please enter your last four digit of ic")
        return ValidationResult(true)
    }

    fun validateNewPasswordRequest(
        phone: String,
        dob: String,
        ic: Int,
        password1: String,
        password2: String
    ) : ValidationResult {
        if (phone.isBlank() && dob.isBlank() && ic===0 && password1.isBlank() && password2.isBlank())
            return ValidationResult(false, "All fields cannot be blank")
        if (phone.isBlank()) return ValidationResult(false, "Please enter your phone number")
        if(dob.isBlank()) return ValidationResult(false, "Please enter your date of birth")
        if(ic===0) return ValidationResult(false, "Please enter your last four digit of ic")
        if(password1.isBlank()) return ValidationResult(false, "Please enter your password")
        if(password2.isBlank()) return ValidationResult(false, "Please enter your password confirmation")
        if(!password1.equals(password2)) return ValidationResult(false, "Password confirmation is not same previous password")
        return ValidationResult(true)
    }

    fun validateTopUpRequest(
        amount: Int,
        method: Int
    ) : ValidationResult {
        if(amount === 0 && method === 0)
            return ValidationResult(false, "All fields cannot be blank")
        if(amount === 0)
            return ValidationResult(false, "Amount is blank")
        if(method === 0)
            return ValidationResult(false, "Method is blank")
        if(!amount.equals(Int) && !method.equals(Int))
            return ValidationResult(false, "Amount and method are not type of integer")
        if(!amount.equals(Int))
            return ValidationResult(false, "Amount is not type of integer")
        if(!method.equals(Int))
            return ValidationResult(false, "Method is not type of integer")
        return ValidationResult(true)
    }
}