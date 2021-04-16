<template>
    <div id="timer-container">
        <div>{{ formattedElapsedTime }}</div>
        <button class="timer-button" @click="start" v-if="!this.isRunning">
            Start
        </button>
        <button class="timer-button" @click="stop" v-if="this.isRunning">
            Stop
        </button>
        <button class="timer-button" @click="reset">Save</button>
        <h4 class="dashboard-link">
            Total Time Spent Reading:
            <br />
            {{ $store.state.total }}
        </h4>
    </div>
</template>

<script>
export default {
    name: "timer",
    data() {
        return {
            elapsedTime: 0,
            timer: undefined,
            isRunning: false,
            total: 0,
        };
    },
    computed: {
        formattedElapsedTime() {
            const date = new Date(null);
            date.setSeconds(this.elapsedTime / 1000);
            const utc = date.toUTCString();
            return utc.substr(utc.indexOf(":") - 2, 8);
        },

        formattedTotal() {
            const date = new Date(null);
            date.setSeconds(this.total / 1000);
            const utc = date.toUTCString();
            return utc.substr(utc.indexOf(":") - 2, 8);
        },
    },
    methods: {
        start() {
            this.timer = setInterval(() => {
                this.elapsedTime += 1000;
            }, 1000);
            this.isRunning = true;
        },

        stop() {
            clearInterval(this.timer);
            this.isRunning = false;
            this.total = this.total + this.timer;
        },

        reset() {
            this.total = this.total + this.elapsedTime;
            this.$store.commit("SAVE_TIME", this.formattedTotal);
            this.elapsedTime = 0;
        },
    },
};
</script>

<style>
#timer-container {
    display: flex;
    flex-direction: column;
    gap: 10px;
    font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
    font-size: 4rem;
    background-color: #a3e4d7;
    border-radius: 20px;
    padding-right: 70px;
    padding-left: 70px;
}

.timer-button {
    color: whitesmoke;
    background: #875f9a;
    box-shadow: 2px 6px #51395c;
    font-weight: bold;
    padding: 5px 10px 5px 10px;
    cursor: pointer;
    font-size: 2.5rem;
    border-radius: 5px;
    text-align: center;
    gap: 10px;
    justify-content: space-around;
    align-content: space-around;
    /* width: 250px; */
}

h4 {
    margin: 10px;
}
</style>
