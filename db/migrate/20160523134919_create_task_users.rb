class CreateTaskUsers < ActiveRecord::Migration
  def change
    create_table :task_users do |t|
      t.string :name
      t.string :password

      t.timestamps null: false
    end
  end
end
