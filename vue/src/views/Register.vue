<template>
    <div
        id="register"
        :style="{
            backgroundImage: `url(${registerImg})`,
            backgroundSize: 'auto',
            backgroundRepeat: 'no-repeat',
            BackgroundPosition: 'center',
        }"
    >
        <form class="form" @submit.prevent="register">
            <div>
                <h1 class="title">Create Account</h1>
            </div>
            <div class="alert alert-danger" role="alert" id="regerror" v-if="registrationErrors">
                {{ registrationErrorMsg }}
            </div>
            <div class="form-body">
                <div class="input-line" style="color:red;">
                    *
                    <input
                        type="text"
                        id="username"
                        class="form-control"
                        placeholder="Username"
                        v-model="user.username"
                        required
                        autofocus
                    />
                </div>
                <div class="input-line" style="color:red;">
                    *
                    <input
                        type="text"
                        id="first-name"
                        class="form-control"
                        placeholder="First Name"
                        v-model="user.firstName"
                        required
                    />
                </div>
                <div class="input-line">
                    <input
                        type="text"
                        id="last-name"
                        class="form-control"
                        placeholder="  Last Name"
                        v-model="user.lastName"
                    />
                </div>
                <div class="input-line" style="color:red;">
                    *
                    <input
                        type="email"
                        id="email-address"
                        class="form-control"
                        placeholder="Email Address"
                        v-model="user.emailAddress"
                        required
                    />
                </div>
                <div class="input-line" style="color:red;">
                    *
                    <input
                        type="password"
                        id="password"
                        class="form-control"
                        placeholder="Password"
                        v-model="user.password"
                        required
                    />
                </div>
                <div class="input-line" style="color:red;">
                    *
                    <input
                        type="password"
                        id="confirmPassword"
                        class="form-control"
                        placeholder="Confirm Password"
                        v-model="user.confirmPassword"
                        required
                    />
                </div>
                <div>
                    <p class="requirements">* Required</p>
                    <div class="finished-link">
                        <router-link :to="{ name: 'login' }">Have an account?</router-link>
                    </div>
                </div>
                <br />
                <div class="center">
                    <button class="button" type="submit" id="button">
                        Create Account
                    </button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import authService from "../services/AuthService.js";
import registerImg from "@/assets/login.png";

export default {
    name: "register",
    data() {
        return {
            user: {
                username: "",
                firstName: "",
                lastName: "",
                emailAddress: "",
                password: "",
                confirmPassword: "",
                role: "user",
            },
            registrationErrors: false,
            registrationErrorMsg: "There were problems registering this user.",
            registerImg,
        };
    },
    methods: {
        register() {
            if (this.user.password != this.user.confirmPassword) {
                this.registrationErrors = true;
                this.registrationErrorMsg = "Password & Confirm Password do not match.";
            } else {
                authService
                    .register(this.user)
                    .then((response) => {
                        if (response.status == 201) {
                            this.$router.push({
                                path: "/login",
                                query: { registration: "success" },
                            });
                        }
                    })
                    .catch((error) => {
                        const response = error.response;
                        this.registrationErrors = true;
                        if (response.status === 400) {
                            this.registrationErrorMsg = "This user already exists, please try again.";
                        }
                    });
            }
        },
        clearErrors() {
            this.registrationErrors = false;
            this.registrationErrorMsg = "There were problems registering this user.";
        },
    },
};
</script>

<style>
#register {
    display: flex;
    flex-direction: column;

    align-items: center;
}

.requirements {
    font-size: 12px;
    color: red;
}

#regerror {
    font-size: 1rem;
    font-weight: bold;
    text-align: center;
}
</style>
