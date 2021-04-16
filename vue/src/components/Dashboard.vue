<template>
    <div>
        <main id="main-grid">
            <div id="current-read">
                <h1 class="dashboard-title">
                    Currently Reading:
                    <br />
                </h1>
                <router-link
                    class="dashboard-link"
                    v-show="book.reading"
                    v-bind:book="book"
                    v-for="book in $store.state.books"
                    v-bind:key="book.title"
                    v-bind:to="{ name: 'book-details', params: { title: book.title } }"
                    >{{ book.title }}
                    <br />
                </router-link>
            </div>
            <div id="conan">
                <iframe
                    width="560"
                    height="315"
                    src="https://www.youtube-nocookie.com/embed/mZHoHaAYHq8?start=1"
                ></iframe>
            </div>
            <div id="timer">
                <timer />
            </div>
        </main>
    </div>
</template>

<script>
import Timer from "./Timer.vue";

export default {
    name: "dashboard",
    components: {
        Timer,
    },
    computed: {
        currentlyReading() {
            return this.$store.books.filter((book) => book.reading == true);
        },
    },
};
</script>

<style>
#main-grid {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 15px;
    grid-template-areas: "current conan timer";
}

#current-read {
    grid-area: current;
    background-color: #a3e4d7;
    border: 1px solid black;
    box-shadow: 8px 10px #51395c;
    border-radius: 20px;
}

#timer {
    grid-area: timer;
    display: flex;
    flex-direction: column;
    gap: 10px;
    font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
    font-size: 4rem;
    background-color: #a3e4d7;
    border: 1px solid black;
    box-shadow: 8px 10px #51395c;
    border-radius: 20px;
    padding-right: 70px;
    padding-left: 70px;
}

#my-books {
    grid-area: books;
}

#conan {
    grid-area: conan;
    background-color: #a3e4d7;
    border: 1px solid black;
    box-shadow: 8px 10px #51395c;
    border-radius: 20px;
    padding: 10px;
}

@media (max-width: 1024px) {
    #main-grid {
        grid-template-columns: 1fr;
        gap: 10px;
        grid-template-areas:
            "current"
            "conan"
            "timer";
    }
}
</style>
