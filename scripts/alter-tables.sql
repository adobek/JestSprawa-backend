alter table app.IssuesStatuses
alter column date set default NOW();

insert into app.Roles
values (3, 'administrator');