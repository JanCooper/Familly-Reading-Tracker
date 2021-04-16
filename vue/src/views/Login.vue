<template>
    <div>
        <h1 class="login-title">
            Welcome To The Dewey Decimal Reading Tracker
        </h1>
        <div
            id="login"
            :style="{
                backgroundImage: `url(${loginImg})`,
                backgroundSize: 'contain',
                backgroundRepeat: 'no-repeat',
                BackgroundPosition: 'center',
            }"
        >
            <form class="form" @submit.prevent="login">
                <h1 class="title">Please Sign In</h1>
                <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
                    Invalid username and password!
                </div>
                <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
                    Thank you for registering, please sign in.
                </div>
                <div class="form-body">
                    <div class="input-line">
                        <input
                            type="text"
                            id="username"
                            class="form-control"
                            placeholder="Username"
                            v-model="user.username"
                            required
                        />
                    </div>
                    <div class="input-line">
                        <input
                            type="password"
                            id="password"
                            class="form-control"
                            placeholder="Password"
                            v-model="user.password"
                            required
                        />
                    </div>
                    <div class="finished-link">
                        <router-link :to="{ name: 'register' }">
                            Register New User
                        </router-link>
                    </div>
                    <div class="center">
                        <button class="button" type="submit">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
        <h2 class="login-approve">Conan The Librarian Approved!</h2>
    </div>
</template>

<script>
import authService from "../services/AuthService.js";
import loginImg from "@/assets/login.png";

export default {
    name: "login",
    components: {},
    data() {
        return {
            user: {
                username: "",
                password: "",
            },
            invalidCredentials: false,
            loginImg,
        };
    },
    methods: {
        login() {
            authService
                .login(this.user)
                .then((response) => {
                    if (response.status == 200) {
                        this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                        this.$store.commit("SET_USER", response.data.user);
                        this.$router.push("/");
                        location.reload();
                    }
                })
                .catch((error) => {
                    const response = error.response;

                    if (response.status === 401) {
                        this.invalidCredentials = true;
                    }
                });
        },
    },
};
</script>

<style>
#login {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.login-title {
    text-align: center;
    font-size: 3rem;
}

.login-approve {
    text-align: center;
    font-size: 2rem;
}
</style>
