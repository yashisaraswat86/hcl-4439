#-----Run stage------
FROM nginx:alpine
COPY nginx.conf /etc/nginx /conf.d/default.com
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80