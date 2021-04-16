<template>
    <div :style="{ backgroundImage: `url(${addBookImg})`, backgroundSize: '36%', backgroundRepeat: 'no-repeat' }">
        <new-book-form />
    </div>
</template>

<script>
import newBookForm from "../components/NewBookForm.vue";
import addBookImg from "@/assets/add-book.png";

export default {
    components: {
        newBookForm,
    },
    data() {
        return {
            addBookImg,
        };
    },
};
</script>
<style></style>
