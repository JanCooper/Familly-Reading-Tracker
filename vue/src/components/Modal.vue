<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal" role="dialog" aria-labelledby="modalTitle" aria-describedby="modalDescription">
                <header class="center title" id="modalTitle">
                    <slot name="header">
                        Success!
                    </slot>
                </header>
                <section class="modal-body" id="modalDescription">
                    <slot name="body">
                        body
                    </slot>
                </section>
                <footer class="modal-footer">
                    <slot name="footer">
                        <button type="button" class="button btn-close" @click="close" aria-label="Close modal">
                            Close
                        </button>
                    </slot>
                </footer>
            </div>
        </div>
    </transition>
</template>

<script>
export default {
    name: "modal",
    methods: {
        close() {
            this.$emit("close");
        },
    },
};
</script>

<style>
.modal-backdrop {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal {
    background: whitesmoke;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
}

.modal-header,
.modal-footer {
    padding: 15px;
    display: flex;
}

.modal-header {
    border-bottom: 1px solid whitesmoke;
    color: #03a678;
    justify-content: space-between;
    align-content: center;
    font-size: 5vw;
    font-weight: bold;
    -webkit-text-stroke: 1px #875f9a;
}

.modal-footer {
    border-top: 1px solid whitesmoke;
    justify-content: center;
}

.modal-body {
    position: relative;
    padding: 20px 10px;
    box-shadow: 10px 10px #51395c;
}

.btn-close {
    color: #03a678;
    background: transparent;
    font-weight: bold;
}
</style>
