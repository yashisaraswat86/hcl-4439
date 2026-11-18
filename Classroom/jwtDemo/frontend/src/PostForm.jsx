import { useActionState, useRef } from "react";
import SubmitButton from "./SubmitButton";
import api from "./Api";

const PostForm = ({ onCreated }) => {
  const formRef = useRef(null);

  const submitAction = async (prev, formData) => {
    const title = formData.get("title")?.trim();
    const content = formData.get("content")?.trim();

    if (!title || !content) {
      return {
        error: "Title and content are required",
        msg: "",
        title: title || "",
        content: content || "",
      };
    }

    try {
      await api.post("/posts", { title, content });
      await onCreated?.();
      formRef.current?.reset();

      return {
        error: null,
        msg: "Post created successfully",
        title: "",
        content: "",
      };
    } catch (error) {
      return {
        error:
          error.response?.data?.error ||
          error.response?.data?.message ||
          "Failed to create post. Please try again.",
        msg: "",
        title,
        content,
      };
    }
  };

  const [state, formAction, isPending] = useActionState(submitAction, {
    error: null,
    msg: "",
    title: "",
    content: "",
  });

  return (
    <form
      ref={formRef}
      action={formAction}
      className="w-full max-w-2xl space-y-5 rounded-2xl border border-white/10 bg-slate-900/80 p-6 shadow-2xl shadow-black/20 backdrop-blur-xl sm:p-8"
    >
      <div className="mb-2">
        <h3 className="text-2xl font-bold tracking-tight text-white">
          Write a Post
        </h3>
        <p className="mt-2 text-sm text-slate-400">
          Share your thoughts and ideas with others.
        </p>
      </div>

      <div className="space-y-2">
        <label
          htmlFor="title"
          className="block text-sm font-medium text-slate-200"
        >
          Title
        </label>
        <input
          type="text"
          name="title"
          id="title"
          placeholder="Enter your post title"
          defaultValue={state.title}
          required
          disabled={isPending}
          className="w-full rounded-xl border border-slate-700 bg-slate-800/80 px-4 py-3 text-white placeholder-slate-500 outline-none transition-all duration-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20 disabled:cursor-not-allowed disabled:opacity-50"
        />
      </div>

      <div className="space-y-2">
        <label
          htmlFor="content"
          className="block text-sm font-medium text-slate-200"
        >
          Content
        </label>
        <textarea
          name="content"
          id="content"
          placeholder="Write your post here..."
          rows={4}
          defaultValue={state.content}
          required
          disabled={isPending}
          className="w-full resize-y rounded-xl border border-slate-700 bg-slate-800/80 px-4 py-3 text-white placeholder-slate-500 outline-none transition-all duration-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20 disabled:cursor-not-allowed disabled:opacity-50"
        />
      </div>

      <SubmitButton />

      {state.msg && (
        <p
          role="status"
          className="rounded-lg border border-emerald-500/20 bg-emerald-500/10 px-4 py-3 text-sm font-medium text-emerald-400"
        >
          {state.msg}
        </p>
      )}

      {state.error && (
        <p
          role="alert"
          className="rounded-lg border border-red-500/20 bg-red-500/10 px-4 py-3 text-sm font-medium text-red-400"
        >
          {state.error}
        </p>
      )}
    </form>
  );
};

export default PostForm;